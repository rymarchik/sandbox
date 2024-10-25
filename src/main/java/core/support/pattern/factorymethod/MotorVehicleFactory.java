package core.support.pattern.factorymethod;

import core.model.vehicle.MotorVehicle;

/**
 * The factory method pattern loosens the coupling code by separating our Product's construction code from the code that uses
 * this Product.
 */
public abstract class MotorVehicleFactory {
    public MotorVehicle create() {
        MotorVehicle vehicle = createMotorVehicle();
        vehicle.build();
        return vehicle;
    }
    protected abstract MotorVehicle createMotorVehicle();
}
