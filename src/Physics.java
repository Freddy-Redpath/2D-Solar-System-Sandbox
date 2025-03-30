class Physics{
    private static final double G = 6.67430e-11;
    public void applyGravity(CelestialBody p1, CelestialBody p2){
        //calculate gravitational force between p1, p2
        double dx = p2.getXPosition() - p1.getXPosition();
        double dy = p2.getYPosition() - p1.getYPosition();
        double distance = Math.sqrt((dx * dx) + dy * dy);
        if (distance == 0) return;//avoid division by 0

        double force = G * p1.getMass() * p2.getMass()/(distance*distance);
        double forceX = force * dx/distance;
        double forceY = force * dy/distance;

        updateVelocity(p1,forceX,forceY,1.0);
        updateVelocity(p2,-forceX,-forceY,1.0);


    }

    public int[] updatePosition(CelestialBody planet, double deltaTime){
        //update the planets position based on velocity and deltaTime
        double dx = planet.getSpeed() * Math.cos(planet.getDirection())*deltaTime;
        double dy = planet.getSpeed() * Math.sin(planet.getDirection())*deltaTime;
        int[] newXY = {(int)(planet.xPosition+dx),(int)(planet.yPosition+dy)};
        return newXY;

    }

    public void updateVelocity(CelestialBody planet, double netForceX, double netForceY, double deltaTime){
        //update the planets velocity based on net forces and delta time
        double accelerationX = netForceX/planet.getMass();
        double accelerationY = netForceY/planet.getMass();
        double newSpeedX = planet.getSpeed() * Math.cos(planet.getDirection()) + accelerationX * deltaTime;
        double newSpeedY = planet.getSpeed() * Math.sin(planet.getDirection()) + accelerationY * deltaTime;
        planet.setSpeed(Math.sqrt(newSpeedX*newSpeedX+newSpeedY*newSpeedY));
        planet.setDirection(Math.atan2(newSpeedY,newSpeedX));


    }
}