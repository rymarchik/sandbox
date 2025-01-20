package core.pattern.abstractfactory;

import core.model.vehicle.ElectricVehicle;
import core.model.vehicle.FutureVehicleElectricCar;
import core.model.vehicle.FutureVehicleMotorcycle;
import core.model.vehicle.MotorVehicle;

public class FutureVehicleCorporation extends Corporation {
    @Override
    public MotorVehicle createMotorVehicle() {
        return new FutureVehicleMotorcycle();
    }

    @Override
    public ElectricVehicle createElectricVehicle() {
        return new FutureVehicleElectricCar();
    }
}
