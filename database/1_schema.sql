DROP TABLE IF EXISTS "test";
DROP SEQUENCE IF EXISTS test_id_seq;
CREATE SEQUENCE test_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1;

CREATE TABLE "public"."test" (
    "id" integer DEFAULT nextval('test_id_seq') NOT NULL,
    CONSTRAINT "test_pkey" PRIMARY KEY ("id")
) WITH (oids = false);
