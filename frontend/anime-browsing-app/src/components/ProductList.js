import React, { useState } from "react";
import { useQuery, gql } from '@apollo/client';

const GET_REVIEWS = gql`
    query GetReviews($product_id: String!){
        reviews(product_id: $product_id) {
            id
            comment
        }
    }
`;


const GET_ANIME = gql`
    query GetAnime($page: Int, $pageSize: Int) {
        anime(page: $page, pageSize: $pageSize) {
            items {
                uid
                title
            }
            totalCount
            page
            pageSize
            totalPages
        }
    }
`;

function ProductList() {
    const [page, setPage] = useState(1);
    const pageSize = 5;
    const { loading, error, data } = useQuery(GET_ANIME, {
        variables: {page, pageSize},
    });
    if (loading) return <p>Loading...</p>;
    if (error) return <p>Error: {error.message}</p>;

    const anime = data?.anime?.items || [];
    const totalCount = data?.anime?.totalCount;
    const totalPages = data?.anime?.totalPages;

    const handleNextPage = () => {
        if (page < totalPages) {
            setPage(page + 1);
        }
    };

    const handlePrevPage = () => {
        if (page > 1) {
            setPage(page - 1);
        }
    };

    return (
        <div>
            <h2>Products</h2>
            <ul>
                {anime.map((item) => (
                    <li key={item.uid}>
                        {item.title}

                    </li>
                ))}
            </ul>
            <div>
                <button onClick={handlePrevPage} disabled={page === 1} >Previous</button>
                <span>Page {page} of {totalPages} (Total: {totalCount} animes)</span>
                <button onClick={handleNextPage} disabled={page===totalPages}>Next</button>
            </div>
        </div>
    );
}

export default ProductList;