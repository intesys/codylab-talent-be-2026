-- Script DDL base per esercizi CodyLab
-- Compatibile con PostgreSQL (facilmente adattabile ad altri DB)

BEGIN;

CREATE TABLE IF NOT EXISTS prodotto (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    descrizione VARCHAR(255) NOT NULL,
    prezzo NUMERIC(10,2) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT ck_prodotto_prezzo_non_negativo CHECK (prezzo >= 0)
);

CREATE TABLE IF NOT EXISTS libro (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    titolo VARCHAR(255) NOT NULL,
    autore VARCHAR(255) NOT NULL,
    lingua VARCHAR(2) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT ck_libro_lingua_valida CHECK (lingua IN ('IT', 'EN'))
);

CREATE INDEX IF NOT EXISTS idx_prodotto_descrizione ON prodotto (descrizione);
CREATE INDEX IF NOT EXISTS idx_libro_titolo ON libro (titolo);
CREATE INDEX IF NOT EXISTS idx_libro_autore ON libro (autore);

COMMIT;

