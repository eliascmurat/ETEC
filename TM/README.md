# Xamarin.Android

Seja bem-vindo !

Esse repositório foi criado para mostrar projetos criados durante as aulas de Tecnologia Mobile (TM). 

#### Projetos:
- IMC
- Gasolina x Etanol
- ViaCEP
- Google Maps
- CRUD

------------

## Projetos

#### Tela de login
A Tela de Login recebe o nome e senha do usuário e verifica se há um registro com essas informações, se não existir o app exibirá uma mensagem falando que o usuário e/ou a senha são inválidos.

![TelaLogin](https://user-images.githubusercontent.com/65032290/94365394-0c402180-00a7-11eb-892a-2d84712d9dd6.png)

![TelaLoginError](https://user-images.githubusercontent.com/65032290/94365424-47425500-00a7-11eb-8f0f-eee79d014e88.png)

------------

#### Tela de registro
Na tela de Registro, você pode realizar o seu cadastro, inserindo os seguintes dados: nome e senha (possui campo para confirmar a senha). Se as senhas forem diferentes o app retornará um aviso, impedindo de realizar o cadastro.

![TelaRegistro](https://user-images.githubusercontent.com/65032290/94365444-6e008b80-00a7-11eb-8494-b4444368cece.png)

![TelaRegistroErro](https://user-images.githubusercontent.com/65032290/94365452-82448880-00a7-11eb-93ed-755940f0e9d4.png)

------------

#### Login com sucesso
Assim que você realizar o login, o app apresenta duas opções: Voltar para a tela de login ou ver os projetos/apps.

![TelaWelcome](https://user-images.githubusercontent.com/65032290/94365463-a6a06500-00a7-11eb-8b72-d1cad8e3c062.png)

![TelaProjects](https://user-images.githubusercontent.com/65032290/94365469-b750db00-00a7-11eb-9e8e-6a5a914c8e5b.png)

------------

#### IMC
Projeto do cálculo de IMC, é um projeto simples, o IMC é calculado dividindo o peso pela altura elevada ao quadrado. Ou seja, de forma mais simples, você multiplica sua altura por ela mesma e depois divide seu peso pelo resultado da última conta.
O resultado do calculo é exibido em uma caixa de aviso, apresentando o seu IMC e sua categoria.
Na tela você pode colocar seus dados como:
- Altura
- Peso 
- Sexo

![TelaIMC](https://user-images.githubusercontent.com/65032290/94365487-cf285f00-00a7-11eb-89fb-521e05fec19e.png)

![TelaIMCResultado](https://user-images.githubusercontent.com/65032290/94365491-dbacb780-00a7-11eb-9b57-d847545ecce2.png)

------------

#### Gasolina x Etanol
Esse projeto tem o intuito de avaliar e descobrir qual tipo de combustível é melhor para o abastecimento do seu veículo, onde suas opçoes são gasolina ou etanol.
O cálculo utilizado para descobrir qual combustível compensa mais, é simples. Calculamos a diferença entre os dois valor dos combustíveis atraves da divisão, se a diferença for maior que 0.7, a gasolina é melhor para abastecer, se for menor que 0,7, o etanol é melhor, se for igual a 0,7, tanto faz o combustível escolhido.

![TelaCombustivel](https://user-images.githubusercontent.com/65032290/94365499-e9623d00-00a7-11eb-83d5-2465f6b12813.png)

OBS: valores do combustível (por litro), é digitado pelo usuário.

------------

#### ViaCEP
O app ViaCep, é um app que utiliza uma API chamada ViaCEP, nesse projeto você pode digitar o CEP, e consultar outras informações, como:

- Logradouro,
- Complemento,
- Bairro,
- Localidade,
- UF,
- DDD

![TelaViaCep](https://user-images.githubusercontent.com/65032290/94365507-072fa200-00a8-11eb-925c-6b937bacb53b.png)

![TelaViaCepErro](https://user-images.githubusercontent.com/65032290/94365514-10207380-00a8-11eb-90c4-b266c7829992.png)

![TelaViaCepSucesso](https://user-images.githubusercontent.com/65032290/94365521-1adb0880-00a8-11eb-9950-d7f1737babb2.png)

O funcionamento é simples, ele faz uma requisição utilizando o metodo GET, e o resultado (se o CEP for valido), é um arquivo JSON, e nele tem as informações relacionada ao CEP digita pelo usuário.

Link para a API: https://viacep.com.br/


------------

#### GoogleMaps
Mais uma vez utilizando um web service, agora a API do Google Maps, nesse projeto eu aprendi um pouco mais sobre as permições, no app eu tive que habilitar o acesso à internet e localização.

![TelaMaps](https://user-images.githubusercontent.com/65032290/94365567-71e0dd80-00a8-11eb-9e43-4fdd1f5981cf.png)

OBS: O app necessita da chave da API do Google Maps, por questões de segurança a chave não está no projeto, segue dois link's de apoio para obter a chave:

https://docs.microsoft.com/en-us/xamarin/android/platform/maps-and-location/maps/maps-api

https://docs.microsoft.com/en-us/xamarin/android/platform/maps-and-location/maps/obtaining-a-google-maps-api-key?tabs=windows

Depois que obter a chave, colocar ela no arquivo strings.xml

------------

#### CRUD
E por último, utilizando o SQLite, um CRUD, onde você pode cadastrar seus livros favoritos, pode alterar eles, e excluir, novamente o projeto simples mas interessante para aprender mais sobre o Xamarin.Android e conexão ao banco de dados.

![TelaCrud](https://user-images.githubusercontent.com/65032290/94365577-7efdcc80-00a8-11eb-9814-9d6fcbe8e548.png)
