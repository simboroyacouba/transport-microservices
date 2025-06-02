const logger = require("pino");
// const dayjs = require("dayjs");

const transport = logger.transport({
  target: 'pino-pretty',
  level: process.env.LOG_LEVEL || 'info',
  options: { colorize: true },
})

const log = logger({ level: process.env.LOG_LEVEL || 'info', }, transport)

module.exports = log;