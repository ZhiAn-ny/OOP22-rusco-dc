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
 * <li> I need to create a new Pair "if the previous exist", following a "line logic" 
 *  cause some {@code}Range{@code} of some ability of {@code}Actor{@code} 
 *  can be obstacolate by some Room's entity's (such as wall, chest, and so on)</li>
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
public final class Pairs {

    private Pairs() {
    }

    /**
     * Given two points, return the major distance between these. 
     * @param a first point
     * @param b second point
     * @return the biggest distance
     */
    private static int computeBiggerDelta(final Pair<Integer, Integer> a, final Pair<Integer, Integer> b) {
        final int deltaRows = b.getX() - a.getX();
        final int deltaCols = b.getY() - a.getY();
        return Math.max(Math.abs(deltaRows), Math.abs(deltaCols));
    }

    /**
     * Given two points, return the smaller distance between these.
     * @param a first point
     * @param b second point
     * @return the smallest distance
     */
    private static int computeLowerDelta(final Pair<Integer, Integer> a, final Pair<Integer, Integer> b) {
        final int deltaRows = b.getX() - a.getX();
        final int deltaCols = b.getY() - a.getY();
        return Math.min(Math.abs(deltaRows), Math.abs(deltaCols));
    }

    /**
     * Given two points, compute all delta of all points that are between these two points.
     * This result should be applied on a point to obtain a "half line" with A -> B direction.
     * Therefore this method creates infinite pairs of values which, applied to a point A, create the respective half-line A-B.
     * This line is a PPLine
     * @param a the point where the ideally "half line" start.
     * @param b the point where the ideally "half line" pass.
     * @return this infinite pairs, wrapped into a Stream.
     */
    private static Stream<Pair<Integer, Integer>> computeInfPPLineDelta(
            final Pair<Integer, Integer> a, 
            final Pair<Integer, Integer> b) {
        final int deltaRows = b.getX() - a.getX();
        final int deltaCols = b.getY() - a.getY();
        final boolean reflect = Math.abs(deltaCols) < Math.abs(deltaRows);
        final boolean increase = reflect && (b.getX() > a.getX()) || !reflect && (b.getY() > a.getY());

        final double angCoeff = (deltaRows * 1.0) / (deltaCols * 1.0);
        final double myAngCoeff = reflect ? 1 / angCoeff : angCoeff;

        return Stream.iterate(0, i -> i = increase ? i + 1 : i - 1)
            .map(i -> reflect 
                ? new Pair<Integer, Integer>(i, (int) Math.round(myAngCoeff * i)) 
                : new Pair<Integer, Integer>((int) Math.round(myAngCoeff * i), i));
    }




    /**
     * This method apply a pre-computed "line of delta" to a point.
     * So this method return the "half line" wrapped into deltaLine applied on the specified point.
     * @param deltaLine the line of delta. (it can be limited or unlimited)
     * @param startPt the point where appliy deltaLine.
     * @return the effective half-line.
     */
    public static Stream<Pair<Integer, Integer>> applyInfLineDelta(
            final Stream<Pair<Integer, Integer>> deltaLine,
            final Pair<Integer, Integer> startPt) {
        return deltaLine.map(p -> new Pair<>(p.getX() + startPt.getX(), p.getY() + startPt.getY()));
    }

    /**
     * Given two point, return an "half line". This line:
     * <ul>
     * <li> start in A </li>
     * <li> have A -> B direction </li>
     * </ul>
     * This half line has PPLine stile.
     * @param a first point 
     * @param b second point
     * @return the half line previosily described
     */
    public static Stream<Pair<Integer, Integer>> computeInfPPLine(
            final Pair<Integer, Integer> a, 
            final Pair<Integer, Integer> b) {
        return applyInfLineDelta(computeInfPPLineDelta(a, b), a);
    }

    /**
     * Create a line in PPLine style.
     * @param a the point where the line start
     * @param b the point where the line stop
     * @return the Stream that wrap this line
     */
    public static Stream<Pair<Integer, Integer>> computePPLine(final Pair<Integer, Integer> a, final Pair<Integer, Integer> b) {
        return computeInfPPLine(a, b).limit(computeBiggerDelta(a, b) + 1);
    }

    /**
     * Given two points, compute all delta of all points that are between these two points.
     * This result should be applied on a point to obtain a "half line" with A -> B direction.
     * Therefore this method creates infinite pairs of values which, applied to a point A, create the respective half-line A-B.
     * This method is based on {@code}computeInfPPLineDelta{@code} method, 
     * but when the coordinate with the smallest offset changes, a point is added to the PPLineDelta to make it BoldLineDelta
     * @param a the point where the ideally "half line" start.
     * @param b the point where the ideally "half line" pass.
     * @return this infinite pairs, wrapped into a Stream.
     */
    private static Stream<Pair<Integer, Integer>> computeInfBoldLineDelta(
            final Pair<Integer, Integer> a, 
            final Pair<Integer, Integer> b) {
        final int deltaRows = b.getX() - a.getX();
        final int deltaCols = b.getY() - a.getY();

        final boolean whorkOnInfX = Math.abs(deltaCols) >= Math.abs(deltaRows);
        final boolean canIncrease = 
            deltaCols < 0 && deltaRows < 0 
            || whorkOnInfX && deltaRows < 0 
            || !whorkOnInfX && deltaRows > 0 && deltaCols < 0;

        final MyIterator<Integer> steps = new MyIterator<>(Stream.iterate(1, i -> i + 1).iterator());
        return computeInfPPLineDelta(a, b)
            .flatMap(p -> {
                if (whorkOnInfX) {
                    if (Math.abs(p.getX()) == steps.getAct()) {
                        final int z = canIncrease ? p.getX() + 1 : p.getX() - 1;
                        steps.next();
                        return Stream.of(new Pair<>(z, p.getY()), p);
                    } else {
                        return Stream.of(p);
                    }
                } else {
                    if (Math.abs(p.getY()) == steps.getAct()) {
                        final int z = canIncrease ? p.getY() + 1 : p.getY() - 1;
                        steps.next();
                        return Stream.of(new Pair<>(p.getX(), z), p);
                    } else {
                        return Stream.of(p);
                    }
                } //TODO - snellire questa lambda
            });
        //return bold;
    }

    /**
     * Given two point, return an "half line" that:
     * <ul>
     * <li> start in A </li>
     * <li> have A -> B direction </li>
     * </ul>
     * This half line has BoldLine stile.
     * @param a first point 
     * @param b second point
     * @return the half line previosily described
     */
    public static Stream<Pair<Integer, Integer>> computeInfBoldLine(
            final Pair<Integer, Integer> a, 
            final Pair<Integer, Integer> b) {
        return applyInfLineDelta(computeInfBoldLineDelta(a, b), a);
    }

    /**
     * Create a line in BoldLine style.
     * @param a the point where the line start
     * @param b the point where the line stop
     * @return the Stream that wrap this line
     */
    public static Stream<Pair<Integer, Integer>> computeBoldLine(final Pair<Integer, Integer> a, final Pair<Integer, Integer> b) {
        return computeInfBoldLine(a, b)
            .limit(computeBiggerDelta(a, b) + computeLowerDelta(a, b) + 1);
    }

    /**
     * TODO - finire commento. 
     * @param center tf
     * @param toRotate tf
     * @param clockwise tf
     * @return tf
     */
    private static Pair<Integer, Integer> rotate90deg(final Pair<Integer, Integer> center,
        final Pair<Integer, Integer> toRotate, 
        final boolean clockwise) {
        final int deltaRows = center.getX() - toRotate.getX();
        final int deltaCols = center.getY() - toRotate.getY(); 
        if (clockwise) {
            return new Pair<Integer, Integer>(center.getX() - deltaCols, center.getY() + deltaRows);
        } else {
            return new Pair<Integer, Integer>(center.getX() + deltaCols, center.getY() - deltaRows);
        }
    }

    /**
     * TODO - finire commento.
     * @param by tf
     * @param to tf
     * @param radius tf
     * @return tf
     */
    public static Stream<Stream<Pair<Integer, Integer>>> computePerpendicularCone(final Pair<Integer, Integer> by,
        final Pair<Integer, Integer> to, 
        final int radius) {
        if (radius <= 0) {
            return Stream.of(Stream.of(by));
        }
        final Pair<Integer, Integer> a = by;
        final Pair<Integer, Integer> bb = rotate90deg(to, a, false);
        final Pair<Integer, Integer> cc = rotate90deg(to, a, true);
        //System.out.println(C + " / " + B);
        final int toSkip = radius - 1 <= 1 ? 1 : radius - 1;
        final Pair<Integer, Integer> b = computeInfPPLine(a, bb).skip(toSkip).findFirst().get();
        final Pair<Integer, Integer> c = computeInfPPLine(a, cc).skip(toSkip).findFirst().get();
        final Pair<Integer, Integer> d = computeBoldLine(a, to).skip(radius).findFirst().get();
        //System.out.println(b + " + " + c);
        final Stream<Pair<Integer, Integer>> bd = computeBoldLine(d, b);
        final Stream<Pair<Integer, Integer>> cd = computeBoldLine(d, c);
        final Stream<Pair<Integer, Integer>> cfr = Stream.concat(bd, cd).distinct();

        final Set<Pair<Integer, Integer>> extremes = Set.of(b, c);

        return cfr.map(p -> extremes.contains(p) 
            ? computePPLine(a, p) 
            : computeBoldLine(a, p));
    }

    /**
     * Help method that compute areas to fill their area, following the logic to fill all the area
     * with line that start to the center and finish to one of the perimetre of the area.
     * @param hotSpots the set of point that identifies area's 
     * @param center the center of the area
     * @return the Stream that wrap all there line, that are also Stream
     */
    private static Stream<Stream<Pair<Integer, Integer>>> fillArea(
            final List<Pair<Integer, Integer>> hotSpots, 
            final Pair<Integer, Integer> center) {
        final Stream<Pair<Integer, Integer>> cfr = Stream.iterate(0, i -> i < hotSpots.size(), i -> i + 1)
        .flatMap(i -> i != (hotSpots.size() - 1)
            ? computePPLine(hotSpots.get(i), hotSpots.get(i + 1)) 
            : computePPLine(hotSpots.get(i), hotSpots.get(0)))
            .distinct();
        return cfr.map(p -> computeBoldLine(center, p));
    }

    /**
     * Compute a circle as a set of all the line 
     * that start in center and finish on one of the circumference points.
     * @param center the center of the circle
     * @param radius their radius
     * @return the Stream that wrap all there line, that are also Stream
     */
    public static Stream<Stream<Pair<Integer, Integer>>> computeCircle(final Pair<Integer, Integer> center, final int radius) {
        final Pair<Integer, Integer> a = computeSingleDelta(center, -radius, true);
        final Pair<Integer, Integer> c = computeSingleDelta(center, +radius, true);
        final Pair<Integer, Integer> b = computeSingleDelta(center, +radius, false);
        final Pair<Integer, Integer> d = computeSingleDelta(center, -radius, false);

        return fillArea(List.of(a, b, c, d), center);
    }

    /**
     * Compute a square as a set of all the line that start in center and finish on one of points of square's perimetre.
     * @param center the center of the square
     * @param offset the ortogonal distance between the center of the square and their sides
     * @return the Stream that wrap all there line, that are also Stream
     */
    public static Stream<Stream<Pair<Integer, Integer>>> computeSquare(final Pair<Integer, Integer> center, final int offset) {
        final Pair<Integer, Integer> dx = computeSingleDelta(center, +offset, false);
        final Pair<Integer, Integer> sx = computeSingleDelta(center, -offset, false);

        final Pair<Integer, Integer> a = computeSingleDelta(sx, -offset, true);
        final Pair<Integer, Integer> b = computeSingleDelta(dx, -offset, true);
        final Pair<Integer, Integer> c = computeSingleDelta(dx, +offset, true);
        final Pair<Integer, Integer> d = computeSingleDelta(sx, +offset, true);

        return fillArea(List.of(a, b, c, d), center);
    }







    /**
     * Compute a point that is "delta" distant by "oldPair", on a specified axis.
     * @param oldPair the start point 
     * @param delta the shift to apply
     * @param yDirection if true, this method modifies X-Pair value, Y-Pair value otherwise
     * @return the new point, as described so far
     */
    private static Pair<Integer, Integer> computeSingleDelta(final Pair<Integer, Integer> oldPair, 
            final int delta, final boolean yDirection) {
        final int x = oldPair.getX();
        final int y = oldPair.getY();
        if (yDirection) {
            return new Pair<>(x + delta, y);
        } else {
            return new Pair<>(x, y + delta);
        }
    }

    /**
     * Given a point, compute the above point.
     * @param oldPair the point that will be below
     * @return the upper point
     */
    public static Pair<Integer, Integer> computeUpPair(final Pair<Integer, Integer> oldPair) {
        return computeSingleDelta(oldPair, -1, true);
    }

    /**
     * Given a point, compute the below point.
     * @param oldPair the point that will be above
     * @return the upper point
     */
    public static Pair<Integer, Integer> computeDownPair(final Pair<Integer, Integer> oldPair) {
        return computeSingleDelta(oldPair, +1, true);
    }

    /**
     * //TODO - finiscimi.
     * @param oldPair 
     * @return the point that is to the left
     */
    public static Pair<Integer, Integer> computeLeftPair(final Pair<Integer, Integer> oldPair) {
        return computeSingleDelta(oldPair, -1, false);
    }

    /**
     * //TODO - finiscimi. 
     * @param oldPair
     * @return the point that is to the right
     */
    public static Pair<Integer, Integer> computeRightPair(final Pair<Integer, Integer> oldPair) {
        return computeSingleDelta(oldPair, +1, false);
    }

    /**
     * Mirrors a point using as a line the one orthogonal to the line connecting the two past points.
     * @param center the start point
     * @param toMirror the point to mirror
     * @return the mirrored point
     */
    public static Pair<Integer, Integer> mirror(final Pair<Integer, Integer> center, final Pair<Integer, Integer> toMirror) {
        final int deltaRows = center.getX() - toMirror.getX();
        final int deltaCols = center.getY() - toMirror.getY();
        return new Pair<>(center.getX() - deltaRows, center.getY() - deltaCols);
    }
}
