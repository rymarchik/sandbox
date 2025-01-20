package core.pattern.factorymethod;

import core.model.vehicle.MotorVehicle;
import core.model.vehicle.Motorcycle;

public class MotorcycleFactory extends MotorVehicleFactory {
    @Override
    protected MotorVehicle createMotorVehicle() {
        return new Motorcycle();
    }
}
