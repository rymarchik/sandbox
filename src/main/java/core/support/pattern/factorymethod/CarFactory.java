package core.support.pattern.factorymethod;

import core.model.vehicle.Car;
import core.model.vehicle.MotorVehicle;

public class CarFactory extends MotorVehicleFactory {
    @Override
    protected MotorVehicle createMotorVehicle() {
        return new Car();
    }
}
