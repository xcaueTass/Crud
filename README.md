# crud

Tetando API:

POST
http://localhost:8080/professionals/professional

{
   "ids" : 1,
   "nomes": "cauee",
   "cargos": 0,
   "dataNascimento" : "1992-11-22T23:50:04",
   "dataCriada" : " ",
   "contatos" : ["teste"]
}

PUT
http://localhost:8080/professionals/1

{
   "ids" : "34",
   "nomes": "cauee",
   "cargos": 0,
   "dataNascimento" : "1992-11-22T23:50:04",
   "dataCriada" : " ",
   "contatos" : ["testee"]
}

GET
http://localhost:8080/professionals/1

DELETE
http://localhost:8080/professionals/1


----------------------------------------------

POST
http://localhost:8080/contacts/contact

{
	"id" : 3,
	"nome" : "Caue",
	"contato": "111111111",
	"professionalId" : 1
}

PUT
http://localhost:8080/contacts/1
{
   "ids" : "1",
   "nomes": "cauee",
   "cargos": 0,
   "dataNascimento" : "1992-11-22T23:50:04",
   "dataCriada" : " ",
   "contatos" : ["teste"]
}

GET
http://localhost:8080/contacts/1

DELETE
http://localhost:8080/contacts/1





