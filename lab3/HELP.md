# Getting Started

Address:
http://localhost:8080/api

Options:
/users/{id}			/{id} - optional
/competition/{id}		/{id} - optional
/health/{id}		/{id} - optional
/result/{id}			/{id} - optional
/participant/{id}	/{id} - optional

Available methods:
GET - recieve all from table, if {id} present - recieve one object
POST - add row to selected table, should contain JSON with object fields
PUT {id} - update row by id with added JSON params
DELETE - delete all rows if possible, if {id} present - delete one row by id
