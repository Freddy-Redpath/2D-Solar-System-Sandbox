public class Physics {
    public static void updatePhysicsTest(){

        //for (Planet planet : Main.solarSystem.getPlanets()) {
  //Main.solarSystem.getPlanets().get(1).setXPosition(getNextPosition);
        //}
    }
    public static int[] getNextPosition(int cx, int cy, int radius, int currentX, int currentY) {
        // Calculate the current angle
        double angle = Math.toDegrees(Math.atan2(currentY - cy, currentX - cx));

        // Increment angle (e.g., move 10 degrees forward)
        angle += 1;

        // Convert angle to radians
        double radians = Math.toRadians(angle);

        // Calculate next x and y positions
        int nextX = (int)(cx + radius * Math.cos(radians));
        int nextY = (int)(cy + radius * Math.sin(radians));

        return new int[]{nextX, nextY};
    }


}
