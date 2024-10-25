package core.support.pattern.abstractfactory;

import core.model.vehicle.ElectricVehicle;
import core.model.vehicle.MotorVehicle;

/**
 * This pattern is commonly used when we start using the Factory Method Pattern, and we need to evolve our system to a more
 * complex system. It centralizes the product creation code in one place.
 */
public abstract class Corporation {
    public abstract MotorVehicle createMotorVehicle();

    public abstract ElectricVehicle createElectricVehicle();
}
