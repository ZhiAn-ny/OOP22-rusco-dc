package it.unibo.ruscodc.utils;


import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

/**
 * A "libary" to compute some geometric shapes, or more generally to perform some algotirms on Pair class.
 * So in this "library" it takes and returns Pairs such that their generics are "Integer"
 * So in this context "point" means a Pair<Integer, Integer>
 * I exploit Java's Stream because:
 * <ul>
 * <li> I need to create a series of Pair object without creating all these objects instantely</li>
 * <li> I need to create a new Pair "if the previous exist", following a "line logic" cause some {@code}Range{@code} of some ability of {@code}Actor{@code} can be obstacolate by some Room's entity's (such as wall, chest, and so on)</li>
 * </ul>
 * 
 * These method are based principally on two tipe of line:
 * <ul>
 * <li> Point-to-Point line: line that bound two Pairs "directly"
 * <li> Bold line: like previous, but each pair of successive Pair is orthogonally adjacent
 * </ul>
 * 
 * So if i need a line between Pair A = [x=3, y=1] and B = [x=1, y=3]:
 * <br>
 * <br>
 * 0 1 2 3 4
 * 1     X
 * 2   X
 * 3 X
 * 4
 * this is a PPline
 * 
 * 0 1 2 3 4
 * 1     X
 * 2   X X
 * 3 X X
 * 4
 * this is a BoldLine
 */
public class Pairs {
    
    /**
     * Given two points, return the major distance between these. 
     * @param A first point
     * @param B second point
     * @return the biggest distance
     */
    private static int computeBiggerDelta(final Pair<Integer, Integer> A, final Pair<Integer, Integer> B){
        final int deltaRows = B.getX() - A.getX();
        final int deltaCols = B.getY() - A.getY();
        return Math.max(Math.abs(deltaRows), Math.abs(deltaCols));
    }

    /**
     * Given two points, return the smaller distance between these 
     * @param A first point
     * @param B second point
     * @return the smallest distance
     */
    private static int computeLowerDelta(final Pair<Integer, Integer> A, final Pair<Integer, Integer> B){
        final int deltaRows = B.getX() - A.getX();
        final int deltaCols = B.getY() - A.getY();
        return Math.min(Math.abs(deltaRows), Math.abs(deltaCols));
    }

    /**
     * Given two points, compute all delta of all points that are between these two points.
     * This result should be applied on a point to obtain a "half line" with A -> B direction.
     * Therefore this method creates infinite pairs of values which, applied to a point A, create the respective half-line A-B.
     * This line is a PPLine
     * @param A the point where the ideally "half line" start.
     * @param B the point where the ideally "half line" pass.
     * @return this infinite pairs, wrapped into a Stream.
     */
    private static Stream<Pair<Integer, Integer>> computeInfPPLineDelta(final Pair<Integer, Integer> A, final Pair<Integer, Integer> B) {
        final int deltaRows = B.getX() - A.getX();
        final int deltaCols = B.getY() - A.getY();
        final boolean reflect = Math.abs(deltaCols) < Math.abs(deltaRows);
        final boolean increase = reflect && (B.getX() > A.getX()) || !reflect && (B.getY() > A.getY());
        
        final double angCoeff = (deltaRows * 1.0) / (deltaCols * 1.0);
        final double myAngCoeff = reflect ? 1/angCoeff : angCoeff;
        
        return Stream.iterate(0, i -> i = increase ? i + 1 : i - 1)
            .map(i -> reflect 
                ? new Pair<Integer, Integer>(i, (int) Math.round(myAngCoeff * i)) 
                : new Pair<Integer, Integer>((int) Math.round(myAngCoeff * i), i));
    }




    /**
     * This method apply a pre-computed "line of delta" to a point.
     * So this method return the "half line" wrapped into deltaLine applied on the specified point.
     * @param deltaLine the line of delta. (it can be limited or unlimited)
     * @param where the point where appliy deltaLine.
     * @return the effective half-line.
     */
    public static Stream<Pair<Integer, Integer>> applyInfLineDelta(final Stream<Pair<Integer, Integer>> deltaLine, final Pair<Integer, Integer> where){
        return deltaLine.map(p -> new Pair<>(p.getX() + where.getX(), p.getY() + where.getY()));
    }

    /**
     * Given two point, return an "half line" that:
     * <ul>
     * <li> start in A </li>
     * <li> have A -> B direction </li>
     * </ul>
     * This half line has PPLine stile
     * @param A first point 
     * @param B second point
     * @return the half line previosily described
     */
    public static Stream<Pair<Integer, Integer>> computeInfPPLine(final Pair<Integer, Integer> A, final Pair<Integer, Integer> B) {
        return applyInfLineDelta(computeInfPPLineDelta(A, B), A);
    }

    /**
     * Create a line in PPLine style
     * @param A the point where the line start
     * @param B the point where the line stop
     * @return the Stream that wrap this line
     */
    public static Stream<Pair<Integer, Integer>> computePPLine(final Pair<Integer, Integer> A, final Pair<Integer, Integer> B) {
        return computeInfPPLine(A,B).limit(computeBiggerDelta(A, B)+1);
    }

    /**
     * Given two points, compute all delta of all points that are between these two points.
     * This result should be applied on a point to obtain a "half line" with A -> B direction.
     * Therefore this method creates infinite pairs of values which, applied to a point A, create the respective half-line A-B.
     * This method is based on {@code}computeInfPPLineDelta{@code} method, but when the coordinate with the smallest offset changes, a point is added to the PPLineDelta to make it BoldLineDelta
     * @param A the point where the ideally "half line" start.
     * @param B the point where the ideally "half line" pass.
     * @return this infinite pairs, wrapped into a Stream.
     */
    private static Stream<Pair<Integer, Integer>> computeInfBoldLineDelta(final Pair<Integer, Integer> A, final Pair<Integer, Integer> B){
        final int deltaRows = B.getX() - A.getX();
        final int deltaCols = B.getY() - A.getY();
        
        final boolean whorkOnInfX = Math.abs(deltaCols) >= Math.abs(deltaRows);
        final boolean canIncrease = 
            deltaCols < 0 && deltaRows < 0 ||
            whorkOnInfX && deltaRows < 0 ||
            !whorkOnInfX && deltaRows > 0 && deltaCols < 0;

        final MyIterator<Integer> steps = new MyIterator<>(Stream.iterate(1, i -> i+1).iterator());
        
        Stream<Pair<Integer, Integer>> bold = computeInfPPLineDelta(A,B)
            .flatMap(p -> {
                if (whorkOnInfX) {
                    if (Math.abs(p.getX()) == steps.getAct()) {
                        int z = canIncrease ? p.getX() + 1 : p.getX() - 1;
                        steps.next();
                        return Stream.of(new Pair<>(z, p.getY()), p);
                    } else {
                        return Stream.of(p);
                    }
                } else {
                    if(Math.abs(p.getY()) == steps.getAct()){
                        int z = canIncrease ? p.getY()+1 : p.getY()-1;
                        steps.next();
                        return Stream.of(new Pair<>(p.getX(),z),p);
                    } else {
                        return Stream.of(p);
                    }
                }//TODO - snellire questa lambda
            });
        return bold;
    }

    /**
     * Given two point, return an "half line" that:
     * <ul>
     * <li> start in A </li>
     * <li> have A -> B direction </li>
     * </ul>
     * This half line has BoldLine stile
     * @param A first point 
     * @param B second point
     * @return the half line previosily described
     */
    public static Stream<Pair<Integer, Integer>> computeInfBoldLine(final Pair<Integer, Integer> A, final Pair<Integer, Integer> B) {
        return applyInfLineDelta(computeInfBoldLineDelta(A, B), A);
    }

    /**
     * Create a line in BoldLine style
     * @param A the point where the line start
     * @param B the point where the line stop
     * @return the Stream that wrap this line
     */
    public static Stream<Pair<Integer, Integer>> computeBoldLine(final Pair<Integer, Integer> A, final Pair<Integer, Integer> B){
        return computeInfBoldLine(A,B)
            .limit(computeBiggerDelta(A, B) + computeLowerDelta(A, B) + 1);
    }
    
    /**
     * TODO - finire commento
     * @param center
     * @param toRotate
     * @param clockwise
     * @return
     */
    private static Pair<Integer, Integer> rotate90deg(final Pair<Integer, Integer> center, final Pair<Integer, Integer> toRotate, final boolean clockwise){
        final int deltaRows = center.getX() - toRotate.getX();
        final int deltaCols = center.getY() - toRotate.getY(); 
        if (clockwise) {
            return new Pair<Integer,Integer>(center.getX() - deltaCols, center.getY() + deltaRows);
        } else {
            return new Pair<Integer,Integer>(center.getX() + deltaCols, center.getY() - deltaRows);
        }
    }

    /**
     * TODO - finire commento
     * @param by
     * @param to
     * @param radius
     * @return
     */
    public static Stream<Stream<Pair<Integer, Integer>>> computePerpendicularCone(final Pair<Integer, Integer> by, final Pair<Integer, Integer> to, final int radius) {
        if(radius <= 0){
            return Stream.of(Stream.of(by));
        }
        final Pair<Integer, Integer> A = by;
        final Pair<Integer, Integer> BB = rotate90deg(to, A, false);
        final Pair<Integer, Integer> CC = rotate90deg(to, A, true);
        //System.out.println(C + " / " + B);
        final int toSkip = radius - 1 <= 1 ? 1 : radius - 1;
        final Pair<Integer, Integer> B = computeInfPPLine(A, BB).skip(toSkip).findFirst().get();
        final Pair<Integer, Integer> C = computeInfPPLine(A, CC).skip(toSkip).findFirst().get();
        final Pair<Integer, Integer> D = computeBoldLine(A, to).skip(radius).findFirst().get();
        System.out.println(B + " + " + C);
        Stream<Pair<Integer, Integer>> BD = computeBoldLine(D, B);
        Stream<Pair<Integer, Integer>> DC = computeBoldLine(D, C);
        Stream<Pair<Integer, Integer>> CFR = Stream.concat(BD, DC).distinct();
      
        Set<Pair<Integer, Integer>> extremes = Set.of(B,C);
       
        return CFR.map(p -> extremes.contains(p) 
            ? computePPLine(A, p) 
            : computeBoldLine(A, p));
    }

    /**
     * Compute a circle as a set of all the line that start in center and finish on one of the circumference points.
     * @param center the center of the circle
     * @param radius this radius
     * @return the Stream that wrap all there line, that are also Stream
     */
    public static Stream<Stream<Pair<Integer, Integer>>> computeCircle(final Pair<Integer, Integer> center, final int radius) {
        final Pair<Integer, Integer> A = computeSingleDelta(center, -radius, true);
        final Pair<Integer, Integer> C = computeSingleDelta(center, +radius, true);
        final Pair<Integer, Integer> B = computeSingleDelta(center, +radius, false);
        final Pair<Integer, Integer> D = computeSingleDelta(center, -radius, false);

        final List<Pair<Integer, Integer>> campioni = List.of(A, B, C, D);

        final Stream<Pair<Integer, Integer>> CFR = Stream.iterate(0, i -> i < campioni.size(), i -> i+1)
            .flatMap(i -> i != (campioni.size()-1)
                ? computePPLine(campioni.get(i), campioni.get(i+1)) 
                : computePPLine(campioni.get(i), campioni.get(0)))
                .distinct()
                .peek(p -> System.out.println(p));
        
        return CFR.map(p -> computeBoldLine(center, p));
    }









    /**
     * Compute a point that is "delta" distant by "oldPair", on a specified axis
     * @param oldPair the start point 
     * @param delta the shift to apply
     * @param yDirection if true, this method modifies X-Pair value, Y-Pair value otherwise
     * @return the new point, as described so far
     */
    private static Pair<Integer, Integer> computeSingleDelta(final Pair<Integer, Integer> oldPair, final int delta, final boolean yDirection){
        final int x = oldPair.getX();
        final int y = oldPair.getY();
        if (yDirection) {
            return new Pair<>(x + delta, y);
        } else {
            return new Pair<>(x, y + delta);
        }
    }

    /**
     * Given a point, compute the above point
     * @param oldPair the point that will be below
     * @return the upper point
     */
    public static Pair<Integer, Integer> computeUpPair(final Pair<Integer, Integer> oldPair) {
        return computeSingleDelta(oldPair, -1, true);
    }

    /**
     * Given a point, compute the below point
     * @param oldPair the point that will be above
     * @return the upper point
     */
    public static Pair<Integer, Integer> computeDownPair(final Pair<Integer, Integer> oldPair) {
        return computeSingleDelta(oldPair, +1, true);
    }

    /**
     * //TODO - finiscimi
     * @param oldPair the point that will be above
     * @return the upper point
     */
    public static Pair<Integer, Integer> computeLeftPair(final Pair<Integer, Integer> oldPair) {
        return computeSingleDelta(oldPair, -1, false);
    }

    /**
     * //TODO - finiscimi 
     * @param oldPair
     * @return
     */
    public static Pair<Integer, Integer> computeRightPair(final Pair<Integer, Integer> oldPair) {
        return computeSingleDelta(oldPair, +1, false);
    }

    /**
     * Mirrors a point using as a line the one orthogonal to the line connecting the two past points
     * @param center the start point
     * @param toMirror the point to mirror
     * @return the mirrored point
     */
    public static Pair<Integer, Integer> mirror(final Pair<Integer, Integer> center, final Pair<Integer, Integer> toMirror){
        final int deltaRows = center.getX() - toMirror.getX();
        final int deltaCols = center.getY() - toMirror.getY();
        return new Pair<>(center.getX() - deltaRows, center.getY() - deltaCols);
    }
}
