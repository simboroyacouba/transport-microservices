const { Trip, CreateTripModel, UpdateTripModel } = require('../models/Trip.model');

module.exports = {
    async getAllTrips(req, res) {
        try {
            const trips = await Trip.findAll({ order: [['createdAt', 'DESC']] });
            res.Response({ data: trips });
        } catch (error) {
            res.status(400).Response({ message: error.message });
        }
    },

    async getTripById(req, res) {
        try {
            const trip = await Trip.findByPk(req.params.id);
            if (!trip) {
                return res.status(404).Response({ message: `Trip ${req.params.id} not found!` });
            }
            res.Response({ data: trip });
        } catch (error) {
            res.status(400).Response({ message: error.message });
        }
    },

    async createTrip(req, res, next) {
        try {
            await CreateTripModel.validateAsync(req.body);
            const newTrip = await Trip.create(req.body);
            res.Response({ data: newTrip });
        } catch (error) {
            next(error);
        }
    },

    async updateTrip(req, res, next) {
        try {
            const trip = await Trip.findByPk(req.params.id);
            if (!trip) {
                return res.status(404).Response({ message: `Trip ${req.params.id} not found!` });
            }
            await UpdateTripModel.validateAsync(req.body);
            await trip.update(req.body);
            res.Response({ data: trip });
        } catch (error) {
            next(error);
        }
    },

    async deleteTrip(req, res) {
        try {
            const id = parseInt(req.params.id);
            if (isNaN(id)) {
                return res.status(400).Response({ message: "Invalid ID format!" });
            }

            const result = await Trip.destroy({ where: { id: id } });
            if (result === 0) {
                return res.status(404).Response({ message: `Trip ${id} not found!` });
            }
            
            res.Response({ message: `Trip ${id} deleted successfully!` });
        } catch (error) {
            res.status(400).Response({ message: error.message });
        }
    },
};