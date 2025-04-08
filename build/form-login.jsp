<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Connexion | Gestion RH</title>
    <!-- Bootstrap 5 local -->
    <link href="assets/css/bootstrap.min.css" rel="stylesheet">
    <style>
        :root {
            --primary: #4e73df;
            --secondary: #858796;
            --success: #1cc88a;
            --light: #f8f9fc;
            --dark: #5a5c69;
        }
        
        body {
            background-color: var(--light);
            height: 100vh;
            display: flex;
            align-items: center;
        }
        
        .login-card {
            border: 0;
            border-radius: 1rem;
            box-shadow: 0 0.15rem 1.75rem 0 rgba(58, 59, 69, 0.15);
            overflow: hidden;
        }
        
        .login-card-img {
            background: url('${pageContext.request.contextPath}/assets/img/login-bg.jpg') center;
            background-size: cover;
            min-height: 200px;
        }
        
        .login-card-body {
            padding: 2rem;
        }
        
        .login-card-title {
            font-weight: 700;
            font-size: 1.5rem;
            margin-bottom: 1.5rem;
            color: var(--dark);
        }
        
        .form-control {
            padding: 0.75rem 1rem;
            border-radius: 0.35rem;
        }
        
        .form-floating label {
            padding: 0.75rem 1rem;
        }
        
        .btn-primary {
            background-color: var(--primary);
            border-color: var(--primary);
            padding: 0.75rem;
            font-weight: 600;
            letter-spacing: 0.05rem;
        }
        
        .btn-primary:hover {
            background-color: #2e59d9;
            border-color: #2e59d9;
        }
        
        .text-primary {
            color: var(--primary) !important;
        }
        
        .small {
            font-size: 0.875rem;
        }
        
        @media (min-width: 992px) {
            .login-card {
                width: 100%;
                max-width: 900px;
            }
            
            .login-card-img {
                min-height: 100%;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-xl-10 col-lg-12 col-md-9">
                <div class="card login-card o-hidden border-0 shadow-lg">
                    <div class="row g-0">
                        <!-- Image Column (hidden on small screens) -->
                        <div class="col-lg-6 d-none d-lg-block login-card-img"></div>
                        
                        <!-- Form Column -->
                        <div class="col-lg-6">
                            <div class="login-card-body p-4 p-md-5">
                                <div class="text-center mb-4">
                                    <h1 class="login-card-title">Connexion</h1>
                                </div>
                                
                                <%-- Affichage des erreurs --%>
                                <% if(request.getAttribute("error") != null) { %>
                                    <div class="alert alert-danger alert-dismissible fade show" role="alert">
                                        ${error}
                                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                                    </div>
                                <% } %>
                                <p>Login : alice</p>
                                <p>password : pass123</p>
                                
                                <form class="user" action="login" method="post">
                                    <div class="form-floating mb-3">
                                        <input type="text" class="form-control" id="login" name="login" 
                                               placeholder="Nom d'utilisateur" required>
                                        <label for="login">Nom d'utilisateur</label>
                                    </div>
                                    
                                    <div class="form-floating mb-4">
                                        <input type="password" class="form-control" id="mdp" name="mdp" 
                                               placeholder="Mot de passe" required>
                                        <label for="mdp">Mot de passe</label>
                                    </div>
                                    
                                    <div class="d-grid mb-3">
                                        <button type="submit" class="btn btn-primary btn-block">
                                            Se connecter
                                        </button>
                                    </div>
                                </form>
                                
                                <hr>
                                
                                <div class="text-center">
                                    <a class="small text-decoration-none" href="#">Mot de passe oublié ?</a>
                                </div>
                                <div class="text-center">
                                    <a class="small text-decoration-none" href="#">Créer un compte</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap 5 JS Bundle with Popper (local) -->
    <script src="${pageContext.request.contextPath}/assets/js/bootstrap.bundle.min.js"></script>
</body>
</html>