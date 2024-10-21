package br.edu.ifsp.scl.vehicleapp.repository

import br.edu.ifsp.scl.vehicleapp.dao.VehicleDao
import br.edu.ifsp.scl.vehicleapp.model.Vehicle
import kotlinx.coroutines.flow.Flow

class VehicleRepository(private val vehicleDao: VehicleDao) {
    val allVehicles: Flow<List<Vehicle>> = vehicleDao.getAllVehicles()

    suspend fun insert(vehicle: Vehicle) {
        vehicleDao.insert(vehicle)
    }

    suspend fun update(vehicle: Vehicle) {
        vehicleDao.update(vehicle)
    }

    suspend fun delete(vehicle: Vehicle) {
        vehicleDao.delete(vehicle)
    }
}
