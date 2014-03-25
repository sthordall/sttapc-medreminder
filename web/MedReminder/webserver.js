var connect = require('connect');
connect.createServer(
    connect.static('../MedReminder/')
).listen(1234);
