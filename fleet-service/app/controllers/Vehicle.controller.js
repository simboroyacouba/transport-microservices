const { Vehicle, CreateVehicleModel, UpdateVehicleModel } = require("../models/Models");
const { generateCodeVehicle} = require("../utils/Functions");

module.exports = {
    async getAllVehicle(req, res) {
        try {
            const vehicles = await Vehicle.findAll({ order: [['createdAt', 'DESC']] });
            res.Response({ data: vehicles });
        } catch (error) {
            res.status(400).Response({ message: error.message });
        }
    },

    async getVehicleById(req, res) {
        try {
            const vehicle = await Vehicle.findByPk(req.params.id);
            if (!vehicle) {
                return res.status(404).Response({ message: `Vehicle ${req.params.id} not found!` });
            }
            res.Response({ data: vehicle });
        } catch (error) {
            res.status(400).Response({ message: error.message });
        }
    },


    async createVehicle(req, res, next) {
        try {
            await CreateVehicleModel.validateAsync(req.body);
            req.body.code = generateCodeVehicle();
            const newVehicle = await Vehicle.create(req.body);
            res.Response({ data: newVehicle });
        } catch (error) {
            next(error);
        }
    },

    async updateVehicle(req, res, next) {
        try {
            const vehicle = await Vehicle.findByPk(req.params.id);
            if (!vehicle) {
                return res.status(404).Response({ message: `Vehicle ${req.params.id} not found!` });
            }
            await UpdateVehicleModel.validateAsync(req.body);
            await vehicle.update(req.body);
            res.Response({ data: vehicle });
        } catch (error) {
            next(error);
        }
    },

    async deleteVehicle(req, res) {
        try {
            // verify if id is valid
            const id = parseInt(req.params.id);
            if (isNaN(id)) {
                return res.status(400).Response({ message: "Invalid ID format!" });
            }

            const result = await Vehicle.destroy({ where: { id: id } });
            if (!result) {
                return res.status(404).Response({ message: `Vehicle ${id} not found!` });
            }
            res.Response({ message: "Vehicle deleted successfully!" });
        } catch (error) {
            console.log(error);

            res.status(400).Response({ message: error.message });
        }
    },
};