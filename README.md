# Buzzao

[![Join the chat at https://gitter.im/Buzzao/buzzao-android](https://badges.gitter.im/Buzzao/buzzao-android.svg)](https://gitter.im/Buzzao/buzzao-android?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)
Esse app tem intuito de facilitar o monitoramento do serviço prestado pelas empresas de ônibus e com esses dados tentar fomentar políticas públicas voltadas para a melhoria do transporte público.

#Próximos Passos 
- [x] Auto-preencher com as linhas da RMR 
- [ ] Passar a utilizar o Firebase
- [ ] Verificar a velocidade máxima da via, com o OpenStreetMap
- [ ] Opção de relatar infração contra o ciclista
- [ ] Registrar reclamação por escrito 
- [ ] Criar "pacote" para cada cidade com a velocidade máxima das vias, possibilitando a verificação da velocidade máxima offline.
- [ ] Tweet automático quando houver ocorrência


#Quer contribuir?
Dê fork no projeto, pegue um item da seção "próximos passos" ou escolha algo por conta própria, crie um branch para tal funcionalidade, desenvolva e envie um pull request! :)

#Setup
Utilizamos o Parse.com como nosso banco, então crie um app nele e em configurações pegue o APP ID e o CLIENT KEY.
Em seguida crie um arquivo chamado config.xml em res/values com as variáveis do tipo string chamadas `parse_app_id` e `parse_client_key` com os respectivos valores.  

#Licença
Código distribuído via "MIT License", para mais informações visite o arquivo LICENSE.txt.




