--usuarios--
INSERT INTO usuario (
    email, nome_completo, senha, data_nasimento, ativo, foto, apelido)
VALUES ('admin@cwi.com.br', 'Arrascaeta', '$2a$10$VrIbJURwINOR5HOrWFFTNOwSILsioRJSuOGAg8Luvr9qZDSOl5JXG', '2000-01-01', true, 'https://storage.googleapis.com/dpw/app/uploads/2019/04/lazy-loading-nativo-imagens-iframes_.jpg', 'admin');

INSERT INTO usuario (
    email, nome_completo, senha, data_nasimento, ativo, foto, apelido)
VALUES ('usuario@cwi.com.br', 'Bruno Henrique', '$2a$10$VrIbJURwINOR5HOrWFFTNOwSILsioRJSuOGAg8Luvr9qZDSOl5JXG','2000-01-01', true, 'https://storage.googleapis.com/dpw/app/uploads/2019/04/lazy-loading-nativo-imagens-iframes_.jpg', 'user');

INSERT INTO usuario (
    email, nome_completo, senha, data_nasimento, ativo, foto, apelido)
VALUES ('estagiario@cwi.com.br', 'Gabigol', '$2a$10$VrIbJURwINOR5HOrWFFTNOwSILsioRJSuOGAg8Luvr9qZDSOl5JXG','2000-01-01', true, 'https://storage.googleapis.com/dpw/app/uploads/2019/04/lazy-loading-nativo-imagens-iframes_.jpg', 'estag');

--solicitacoes --
INSERT INTO solicitacoes(usuario_solicitacao_id, usuario_id) VALUES (3, 1);
INSERT INTO solicitacoes(usuario_solicitacao_id, usuario_id) VALUES (3, 2);

--amizades--
INSERT INTO amizade(amigo_id, usuario_id) VALUES (1, 2);
INSERT INTO amizade(amigo_id, usuario_id) VALUES (2, 1);

--postagens--
INSERT INTO postagem(
    data_postagem, foto, legenda, privada, usuario_id)
VALUES (CURRENT_TIMESTAMP, 'https://storage.googleapis.com/dpw/app/uploads/2019/04/lazy-loading-nativo-imagens-iframes_.jpg', 'Ao vivo no Rio de janeiro', FALSE, 1);

INSERT INTO postagem(
    data_postagem, foto, legenda, privada, usuario_id)
VALUES (CURRENT_TIMESTAMP, 'https://storage.googleapis.com/dpw/app/uploads/2019/04/lazy-loading-nativo-imagens-iframes_.jpg', 'Fala nação rubro negra', FALSE, 1);

INSERT INTO postagem(
    data_postagem, foto, legenda, privada, usuario_id)
VALUES (CURRENT_TIMESTAMP, 'https://storage.googleapis.com/dpw/app/uploads/2019/04/lazy-loading-nativo-imagens-iframes_.jpg', 'Diretamente do maracanã', FALSE, 2);

--curtida--
INSERT INTO curtida(postagem_id, usuario_id) VALUES (1, 2);
INSERT INTO curtida(postagem_id, usuario_id) VALUES (2, 2);
INSERT INTO curtida(postagem_id, usuario_id) VALUES (3, 1);

--comentarios--
INSERT INTO comentario(postagem_id, usuario_id, comentario) VALUES (1, 1, 'Vamos flamengo. p1-u1');
INSERT INTO comentario(postagem_id, usuario_id, comentario) VALUES (1, 2, 'Hoje eh dia de jogo. p1-u2');
INSERT INTO comentario(postagem_id, usuario_id, comentario) VALUES (1, 3, 'Raça até o fim timee. p1-u3');
INSERT INTO comentario(postagem_id, usuario_id, comentario) VALUES (3, 1, 'comentando a publi 3 usuario 1');