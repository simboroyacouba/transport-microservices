const { Sequelize, DataTypes, Model } = require("sequelize");
const Joi = require("joi");
const { DB } = require("../configs/Database");

const Driver = DB.define(
    "Driver",
    {
        id: {
            type: DataTypes.INTEGER,
            autoIncrement: true,
            primaryKey: true,
        },
        nom_prenom: {
            type: DataTypes.STRING,
            allowNull: false,
        },
        numero_permis: {
            type: DataTypes.STRING,
            allowNull: false,
            unique: true,
        },
        telephone: {
            type: DataTypes.STRING,
            allowNull: false,
        },
        email: {
            type: DataTypes.STRING,
            allowNull: true,
            defaultValue: null,
        },
        status: {
            type: DataTypes.ENUM("active", "inactive"),
            allowNull: false,
            defaultValue: "active",
        }
    },
    {
        createdAt: "createdAt",
        updatedAt: "updatedAt",
        tableName: "drivers",
    }
);

const CreateDriverModel = Joi.object({
    nom_prenom: Joi.string().required(),
    numero_permis: Joi.string().required(),
    telephone: Joi.string().required(),
    email: Joi.string().email().optional().allow(null),
    status: Joi.string().valid("active", "inactive").default("active"),
});

const UpdateDriverModel = Joi.object({
    nom_prenom: Joi.string().optional(),
    numero_permis: Joi.string().optional(),
    telephone: Joi.string().optional(),
    email: Joi.string().email().optional().allow(null),
    status: Joi.string().valid("active", "inactive").optional(),
});

module.exports = {
    Driver,
    CreateDriverModel,
    UpdateDriverModel
};