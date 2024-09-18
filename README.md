# Machine Manager


Control your machines easily and quickly. The objective of the project is to know where 
your machines are or who they are with.

![](.dev/static/robocop.webp)

-------

## API Contract

cadastro de machine
POST /machines
````json
{
    "brand": "MAC",
    "inUse": false,
    "zuperId": "123465"
}
````

update de machine
PUT /machine
````json
{
    "registerId": "12B234",
    "brand": "Dell",
    "inUse": true,
    "zuperId": 5342
}
````

visualizar uma machine específica
GET /machine/{registerId}

json de resposta:
````json
{
    "registerId": "12B234",
    "brand": "Dell",
    "inUse": true,
    "zuper": {
        "zuperId": 9845,
        "name": "Angelica Gomes",
        "address": "Rua Anetota, 23",
        "postalCode": "1024020"
    }
}
````

Visualizar a lista com todas as machines
GET /machines

json de resposta
````json
[
    {
        "registerId": "12B234",
        "brand": "Dell",
        "inUse": true,
        "zuper": {
            "zuperId": 12974,
            "name": "Jeyciane Tomas",
            "address": "Av Joaquim Eugenio de Lima, 692",
            "postalCode": "1033049"
        }
    },
    {
        "registerId": "W23B2",
        "brand": "MAC",
        "inUse": false,
        "zuper": null
    },
    {
        "registerId": "874WD",
        "brand": "DELL",
        "inUse": false,
        "zuper": null
    },
    {
        "registerId": "67TYO",
        "brand": "DELL",
        "inUse": true,
        "zuper": {
            "zuperId": 5342,
            "name": "Marcelo Vieira",
            "address": "Rua São Sepé, 178",
            "postalCode": "2043010"
        }
    }
]
````

