CREATE TABLE "competition" (
  "id" int PRIMARY KEY,
  "name" varchar,
  "place_id" int,
  "participant_id" int,
  "day" date
);

CREATE TABLE "place" (
  "id" int PRIMARY KEY,
  "name" varchar,
  "country_id" int
);

CREATE TABLE "country" (
  "id" int PRIMARY KEY,
  "name" varchar
);

CREATE TABLE "result" (
  "competition_id" int,
  "participant_id" int,
  "result" int,
  PRIMARY KEY ("competition_id", "participant_id")
);

CREATE TABLE "participant" (
  "id" int PRIMARY KEY,
  "name" varchar,
  "surname" varchar,
  "country_id" int
);

ALTER TABLE "competition" ADD FOREIGN KEY ("place_id") REFERENCES "place" ("id");

ALTER TABLE "participant" ADD FOREIGN KEY ("country_id") REFERENCES "country" ("id");

ALTER TABLE "result" ADD FOREIGN KEY ("competition_id") REFERENCES "competition" ("id");

ALTER TABLE "result" ADD FOREIGN KEY ("participant_id") REFERENCES "participant" ("id");

ALTER TABLE "place" ADD FOREIGN KEY ("country_id") REFERENCES "country" ("id");

