**Configuração do ambiente**

Programas necessários:

1. GIT
2. Android Studio

Trazer o projeto do GIT

1. Com o CMD aberto, navegue até a pasta onde ficam os seus projetos em android.

2. Use o comando -> git config --global user.name "<seu nome de usuário>" <- coloque o nome de seu usuário.

3. Use o comando -> git config --global user.email seuemaildobitbucket@example.com <-

4. Utilize o comando -> git clone "<endereço do prjeto>" <-para clonar o projeto do git para seu computador(o endereço do projeto fica na aba "Overview" do
bit bucket.

Como comitar alterações do seu código no projeto.

1. Utilize o comando -> git status <- este comando verifica seu projeto e retorna informações sobre o mesmo.

2. Utilize o comando -> git pull <- este comando é muito importante, ele traz tudo o que os outros do grupo fizeram para a sua máquina.

3. Utilize o comando -> git checkout develop <- este comando trocará você para o branch de develop.

IMPORTANTE: SEMPRE, SEMPRE, SEMPRE FAÇA TODAS AS ALTERAÇÕES NO CÓDIGO NO BRANCH DEVELOP NUNCA, NUNCA, NUNCA, NUNCA, NUNCA NO MASTER

4. Utilize o comando -> git add . <- para adicionar o que você fez ao cabeçalho do git.

5. Utilize o comando -> git commit -m "MENSAGEM DE SEU COMMIT" <- para commitar no branch develop.

6. Agora faça todos os testes necessários antes de partir para o branch master, todos os testes.

7. Use o comando -> git push <- para jogar as coisas no server.

8. Por fim, faça o merge através do painel do bitbucket na aba Branches