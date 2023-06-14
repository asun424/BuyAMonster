import {AuthProvider} from "./context/AuthContext";
import { Routes, Route } from "react-router-dom";
import NavBar from "./features/NavBar";
import MainPage from "./features/MainPage";
import AllProducts from "./features/AllProducts";
import SingleProduct from "./features/SingleProduct";
import Profile from "./features/Profile";
import SignUpLogin from "./features/SignUpLogin";

const AppRoutes = () => {
  return (
    <>
      <AuthProvider>
        <NavBar />
        <Routes>
          <Route path="/" element={<MainPage />} />
          <Route path="/allproducts" element={<AllProducts/>}/>
          <Route path="/product/:productId" element={<SingleProduct/>}/>
          <Route path="/profile/product/person" element={<Profile/>}/>
          <Route path="/login" element={<SignUpLogin/>}/>
          <Route path="/signup" element={<SignUpLogin/>}/>
        </Routes>
      </AuthProvider>
    </>
  );
};

export default AppRoutes;
