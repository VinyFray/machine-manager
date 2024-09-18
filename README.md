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

### Tarefas

1. Atualizar a documentação com informações do CRUD dos zupers
2. Implementar validações para o cadastro de machines e zupers
   3. zuper: Nome é obrigatório e não pode ser menor de 5 letras e deve contar nome e sobrenome
   4. machine: quando inUser for true zuperId deve ser obrigatório
   5. machine: Brand é campo obrigatório
   6. zuper: address e postalcode são campos obrigatório e postal code deve ter validação de CPF
7. Ainda não é possível desvincular um zuper de um computador, deve ser implementada essa funcionalidade (tarefa mais dificil, envolve o repository)
8. Toda mensagem de validação deve ser enviada ao usuario em formado JSON usando o status code de 422


## Info que pode ajudar.

1. se um erro envolvendo um dos arquivos de leitura xlsx acontecer uma possível solucação é usar o checkout do git para restaurar o arquivo
````gitexclude
git checkout nome_do_arquivo.xlsx
````