package core.support.pattern.abstractfactory;

import core.model.vehicle.ElectricVehicle;
import core.model.vehicle.MotorVehicle;
import core.model.vehicle.NextGenElectricCar;
import core.model.vehicle.NextGenMotorcycle;

public class NextGenCorporation extends Corporation {
    @Override
    public MotorVehicle createMotorVehicle() {
        return new NextGenMotorcycle();
    }

    @Override
    public ElectricVehicle createElectricVehicle() {
        return new NextGenElectricCar();
    }
}