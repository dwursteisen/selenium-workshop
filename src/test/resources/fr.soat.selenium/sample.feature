#language: fr

Fonctionnalité: Pouvoir se logguer sur l'administration

  Scénario: : Un utilisateur déjà enregistrer dans l'administration essaye de se logguer sur DotClear
    Etant donné un utilisateur avec le login "azerty" et le mot de passe "azerty"
    Quand l'utilisateur se loggue sur la page d'authentification
    Alors l'accès à l'administration est autorisé


  Scénario: Un utilisateur ajoute un billet de blog
    Etant donné un utilisateur avec le login "azerty" et le mot de passe "azerty"
    Quand l'utilisateur se loggue sur la page d'authentification
    Lorsque l'utilisateur choisi d'écrire un nouveau bilet de blog
    Et que le bilet de blog a pour titre "Ceci est un test Cucumber"
    Et que le billet de blog a pour contenu "Ceci est un contenu Cucumber"
    Et que le billet est publié
    Alors le billet de blog est lisible sur le blog avec le titre "Ceci est un test Cucumber"


