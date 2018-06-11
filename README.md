# Readme - App para ramo de alimentos

* Para executar o projeto, altere o valor da propriedade **BASE_URL** no arquivo _build.grade_ (app) para apontar para o ip onde o servidor está rodando.

* Entregáveis:
	- **[x] Implementação dos requisitos**
	- **[x] Relatório simples com funcionalidades a mais a serem implementadas no futuro:**
		
        *Para o futuro precisaremos implementar a parte de feedback para o usuário, como progressbar e telas de loading; tratamento de erro; verificação do estado da conexão; possibilidade de remover itens do carrinho; fechar a compra e realizar o pagamento.*
	- **[x] Relatório simples de justificativas para escolha do design de código, dependências e melhorias no Servidor para melhor uso de dados no Aplicativo:**
		* Justificativas
        
        	*Optei por usar arquitetura MVP visando obter os benefícios da separação do código em camadas, isolando código do **M**odel com seus POJOS e serviços da **V**iew com seus layouts e do **P**resenter que fica responsável por fazer a comunicação entra view e o model.*
        
        	*Outra vantagem desejada foi a possibilidade de executar testes unitários na View, no Presenter e no Model graças ao desacoplamento obtido através do uso de **interfaces.***
         * Dependências

			*Este projeto está configurado com as seguintes dependências:*
    		* com.squareup.retrofit2:retrofit:2.4.0 - Para conexão REST com o servidor;
    		* com.squareup.retrofit2:converter-gson:2.1.0 - Conversor usado pelo retrofit para transformar JSON em objetos;
    		* com.android.support:recyclerview-v7:25.1.1 - Para montar a recyclerview na UI;
    		* com.github.bumptech.glide:glide:3.8.0 - Para fazer o load e cache de imagens no android;
    		* com.squareup.okhttp3:logging-interceptor:3.9.0 - Pacote de log do OkHttp usado no projeto como LogHelper para o retrofit.

		* Melhorias no Servidor

			*Como melhoria no servidor, eu indicaria uma refatoração para devolver objetos completos, por exemplo: Retornar dados dos sanduíches já com a lista de ingredientes preenchidas e não apenas o *id*. Isso evita múltiplas chamadas ao servidor.*
            
            *Outro ponto a se considerar é deixar o servidor responsável pelo cálculo do preço do sanduíche e a aplicação de descontos/promoções. Da forma como foi feito, cada plataforma (android, iOS e web) teria sua própria implementação e minha proposta é centralizar a lógica, unificando o código.*
 
	- **[ ] Os testes automatizados devem ser executados por algum modelo de integração contínua:** N/A
	- **[ ] Implementação de Cache:** N/A

