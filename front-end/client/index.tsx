import { createRoot } from "react-dom/client";
import { BrowserRouter } from "react-router-dom";
import "../public/style.css"
import AppRoutes from "./AppRoutes";

const root = createRoot(document.getElementById("app")!);

root.render(
  <BrowserRouter>
    <AppRoutes/>
  </BrowserRouter>
);
