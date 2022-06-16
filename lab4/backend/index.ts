import express from "express";
import { json } from "body-parser";
import mongoose, { ConnectOptions } from "mongoose";
import dotenv from "dotenv";
import { errorHandler } from "./middlewares/error-handler.middleware";
import { apiRouter } from "./routes/api.routes";
import cors from 'cors';


const app = express();

app.use(json());
app.use(cors({origin: '*'}))
app.use("/api/products", apiRouter);

dotenv.config();

const PORT = process.env.PORT || 3000;
const uri = process.env.DB_URL || "";

mongoose.connect(uri, {
    useNewUrlParser: true,
    useUnifiedTopology: true,
  } as ConnectOptions,
  (err) => {
    if (err)
      console.log("MongoDB error: " + err);
    else
      console.log("MongoDB connected successfully");
  }
);

app.use(errorHandler);

app.listen(PORT, () => {
  console.log(`Server is listening on host: http://localhost:${PORT}`);
});
