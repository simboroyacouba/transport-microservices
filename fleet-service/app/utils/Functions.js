const { v4: uuidv4 } = require('uuid');

function generateCodeVehicle() {
    const uuid = uuidv4().replace(/-/g, ''); // Remove dashes from the UUID
    const isbn = uuid.substring(0, 10);
    return isbn;
}

module.exports = {generateCodeVehicle}