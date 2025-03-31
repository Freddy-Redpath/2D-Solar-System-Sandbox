class Physics {
    private static final double G = 6.67430e-11;

    public void applyGravity(int p1Index, int p2Index) {
        CelestialBody p1 = SolarSystem.CelestialBodies.get(p1Index);
        CelestialBody p2 = SolarSystem.CelestialBodies.get(p2Index);
        //calculate gravitational force between p1, p2

        double dx = p2.getXPosition() - p1.getXPosition();
        double dy = p2.getYPosition() - p1.getYPosition();
        double distance = Math.sqrt((dx * dx) + dy * dy);
        if (distance == 0) return;//avoid division by 0

        double force = G * p1.getMass() * p2.getMass() / (distance * distance);
        double forceX = force * dx / distance;
        double forceY = force * dy / distance;

        updateVelocity(p1Index, forceX, forceY, 1.0);
        updateVelocity(p2Index, -forceX, -forceY, 1.0);
    }

    public void updateVelocity(int i, double netForceX, double netForceY, double deltaTime) {
        //update the planets velocity based on net forces and delta time
        CelestialBody body = SolarSystem.CelestialBodies.get(i);
        double accelerationX = netForceX / body.getMass();
        double accelerationY = netForceY / body.getMass();
        double newSpeedX = body.getSpeed() * Math.cos(body.getDirection()) + accelerationX * deltaTime;
        double newSpeedY = body.getSpeed() * Math.sin(body.getDirection()) + accelerationY * deltaTime;
        body.setSpeed(Math.sqrt(newSpeedX * newSpeedX + newSpeedY * newSpeedY));
        body.setDirection(Math.atan2(newSpeedY, newSpeedX));


    }

    public double[] updatePosition(CelestialBody planet, double deltaTime) {
        //update the planets position based on velocity and deltaTime
        double dx = planet.getSpeed() * Math.cos(planet.getDirection()) * deltaTime;
        double dy = planet.getSpeed() * Math.sin(planet.getDirection()) * deltaTime;
        return new double[]{(planet.getXPosition() + dx), (planet.getYPosition() + dy)};

    }
}