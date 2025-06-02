const { Driver, CreateDriverModel, UpdateDriverModel } = require("../models/Driver.model");

module.exports = {
    async getAllDrivers(req, res) {
        try {
            const drivers = await Driver.findAll({ order: [['createdAt', 'DESC']] });
            res.Response({ data: drivers });
        } catch (error) {
            res.status(400).Response({ message: error.message });
        }
    },

    async getDriverById(req, res) {
        try {
            const driver = await Driver.findByPk(req.params.id);
            if (!driver) {
                return res.status(404).Response({ message: `Driver ${req.params.id} not found!` });
            }
            res.Response({ data: driver });
        } catch (error) {
            res.status(400).Response({ message: error.message });
        }
    },

    async createDriver(req, res, next) {
        try {
            await CreateDriverModel.validateAsync(req.body);
            const newDriver = await Driver.create(req.body);
            res.Response({ data: newDriver });
        } catch (error) {
            next(error);
        }
    },

    async updateDriver(req, res, next) {
        try {
            const driver = await Driver.findByPk(req.params.id);
            if (!driver) {
                return res.status(404).Response({ message: `Driver ${req.params.id} not found!` });
            }
            await UpdateDriverModel.validateAsync(req.body);
            await driver.update(req.body);
            res.Response({ data: driver });
        } catch (error) {
            next(error);
        }
    },

    async deleteDriver(req, res) {
        try {
            const id = parseInt(req.params.id);
            if (isNaN(id)) {
                return res.status(400).Response({ message: "Invalid ID format!" });
            }

            const result = await Driver.destroy({ where: { id: id } });
            if (result === 0) {
                return res.status(404).Response({ message: `Driver ${id} not found!` });
            }
            
            res.Response({ message: `Driver ${id} deleted successfully!` });
        } catch (error) {
            res.status(400).Response({ message: error.message });
        }
    },
};