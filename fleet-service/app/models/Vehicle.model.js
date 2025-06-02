const { Sequelize, DataTypes, Model } = require("sequelize");
const Joi = require("joi");
const { DB } = require("../configs/Database");



const Vehicle = DB.define(
    "Vehicle",
    {
        id: {
            type: DataTypes.INTEGER,
            autoIncrement: true,
            primaryKey: true,
        },
        code: {
            type: DataTypes.STRING,
            allowNull: false,
            unique: true,
        },
        marque: {
            type: DataTypes.STRING,
            allowNull: true,
            defaultValue: null,
        },
        type: {
            type: DataTypes.ENUM("bus", "mini-bus", "van", "truck", "car"),
            allowNull: false,
        },
        status: {
            type: DataTypes.ENUM("available", "in maintenance", "on trip"),
            allowNull: false,
            defaultValue: "available",
        },
        currentLocation: {
            type: DataTypes.JSON,
            allowNull: true,
            defaultValue: null,
            validate: {
                isJSON: true
            }
        },
        lastMaintenanceDate: {
            type: DataTypes.DATE,
            allowNull: true,
        },
        capacity: {
            type: DataTypes.INTEGER,
            allowNull: false,
            defaultValue: 0,
        }

    },
    {
        createdAt: "createdAt",
        updatedAt: "updatedAt",
        tableName: "vehicles",
    }
);



const CreateVehicleModel = Joi.object({
    code: Joi.string().required().max(20),
    type: Joi.string().required().valid("bus", "mini-bus", "van", "truck", "car"),
    marque: Joi.string().optional().max(50),
    status: Joi.string().required().valid("available", "in maintenance", "on trip"),
    currentLocation: Joi.object().optional().pattern(
        Joi.string(),
        Joi.number()
    ).max(1000), // Example: { "latitude": 12.34, "longitude": 56.78 }
    lastMaintenanceDate: Joi.date().optional(),
    capacity: Joi.number().required().min(1).max(1000)
});

const UpdateVehicleModel = Joi.object({
    code: Joi.string().optional().max(20),
    type: Joi.string().optional().valid("bus", "mini-bus", "van", "truck", "car"),
    marque: Joi.string().optional().max(50),
    status: Joi.string().optional().valid("available", "in maintenance", "on trip"),
    currentLocation: Joi.object().optional().pattern(
        Joi.string(),
        Joi.number()
    ).max(1000), // Example: { "latitude": 12.34, "longitude": 56.78 }
    lastMaintenanceDate: Joi.date().optional(),
    capacity: Joi.number().optional().min(1).max(1000)
});


module.exports = {
    Vehicle,
    CreateVehicleModel,
    UpdateVehicleModel,
};