# Desafio Backend

Para rodar o projeto, basta clonar o repositório (ou fazer download), alterar o nome da database, o usuário e senha do banco (no arquivo application.properties), e executar o método main do projeto, localizado na classe 'DesafioBackendApplication';

O projeto foi estruturado em diversas pastas, sendo elas: 

* Controller: contém todos os controllers do sistemas, e também o ControllerAdvice;
* Ennumeration: contém todos os ennumerations utilizados no projeto; 
* Exception: contém as exceções personalizadas;
* Model: 
    -contém as entidades utilizadas no sistema; 
    - Além de outra pasta interna, chamada 'filter', onde possui as classes utilizadas nos filtros;
* Repository: 
    - contém as interfaces que estendem o JpaRepository, e fazem a comunicação com o banco; 
    - contém também interfaces customizadas utilizadas para a filtragem dos registros, utilizadno QueryDSL;
* Service: contém os serviços que fazem a comunicação entre os controllers e os repositorys, além de conter algumas regras de negócio.
* Util: contém apenas uma classe utilizada para facilitar o uso do QueryDSL nas filtragens.

Foi criado duas classes de testes, sendo elas: PedidoTests e ProdutoTests. 
Foi desenvolvido uma bateria de testes em ambas as classes, para testar as principais rotinas do sistema.

Todo o CRUD do sistema, utiliza classes abstratas para a sua padronização, como o CrudService e CrudController. 
Portanto, as implementações dos crud's estão dentro dessas classes classes. 

Foi realizado todas as validações, seguindo o escopo da prova nível III;

O cálculo de desconto foi desenvolvido dentro da classe PedidoServiceImpl (que implementa PedidoService).
O método responsável pelo cálculo, recebe por parâmetro o percentual de desconto que será aplicado, e o pedido que será aplicado o desconto.
Será verificado se o pedido ainda está com status 'Aberto', caso contrário retorna um mensagem, alertando o usuário sobre isso.
Caso todos os produtos do pedido forem do tipo 'Serviço', não será realizado o desconto, e também retornará uma mensagem de erro.
Fora as validações mencionadas acima, o método basicamente irá somar os valores dos produtos que não são 'Serviço', e aplicará o valor total do desconto no pedido.
