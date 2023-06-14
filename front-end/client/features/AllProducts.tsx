import { ReactElement, useEffect, useState } from "react"
import axios from "axios"
import { productObject } from "../interfaces";

const AllProducts = () : ReactElement=> {
    const [products, setProducts]  = useState<productObject[]|null>(null);

    useEffect(
        () => {
            const getAllProducts = async  () => {
              const { data }  =  await axios.get("http://localhost:8080/api/product");
              console.log(data);
              setProducts(data);
            } 
            getAllProducts()
        }, []
    )

    return (
        <>
            <div className="grid grid-rows-6 grid-flow-col gap-4">
                {!products ?
                    "We are currently out of monssters!" : products.map((product: productObject, index: number): ReactElement => (
                            <a href={`/product/${product.id}`} key={index} className="h-fit w-fit border-2">
                                <img src={product.image}/>
                                <div>{product.name}</div>
                            </a>
                    )) }
            </div>
        </>

    )
}

export default AllProducts