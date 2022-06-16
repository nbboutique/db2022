import express from "express";
import { ApiController } from "../controllers/api.controller";

const router = express.Router();

const controller = new ApiController();

// Create
router.post("/", async (req, res, next) => {
  await controller.create(req, res, next);
});

// Get by ID
router.get("/:id", async (req, res, next) => {
  await controller.getById(req.params.id, req, res, next);
});

// Get all
router.get("/", (req, res) => {
  controller.getAll(res);
});

// Update
router.put("/:id", async (req, res, next) => {
  await controller.update(req.params.id, req, res, next);
});

// Delete
router.delete("/:id", (req, res) => {
  controller.delete(req.params.id, res);
});

export { router as apiRouter };
