-- 5. Tabela de Histórico de Partidas / Runs (Para Ranking)
CREATE TABLE IF NOT EXISTS partida (
    id SERIAL PRIMARY KEY,
    jogador_id INT NOT NULL REFERENCES jogador(id) ON DELETE CASCADE,
    pontuacao_final INT NOT NULL,
    moedas_ganhas INT NOT NULL,
    data_partida TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- INSERÇÃO DOS 4 PERSONAGENS PADRÃO (Nomes exatos das telas do jogo)
INSERT INTO jogador (nome, moedas, pontos_estilo) VALUES
('MARIANA', 1000, 0),
('MAYARA', 1000, 0),
('BEATRIZ', 1000, 0),
('MARCO', 1000, 0)
ON CONFLICT (nome) DO NOTHING;

-- INSERÇÃO DE ITENS INICIAIS DE TESTE
INSERT INTO item (nome, tipo, preco, caminho_imagem) VALUES
('Roupa Casual 1', 'ROUPA', 50, 'marco_roupa1.png'),
('Roupa Elegante 2', 'ROUPA', 100, 'mariana_roupa2.png'),
('Vestido de Festa 3', 'ROUPA', 150, 'mayara_roupa3.png'),
('Conjunto Esportivo 4', 'ROUPA', 200, 'beatriz_roupa4.png')
ON CONFLICT DO NOTHING;