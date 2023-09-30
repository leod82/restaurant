import React, { useEffect, useState } from "react";
import OrderService from "../../service/history-order/historyOrder";
import { decodeToken } from "../../service/base/decodeToken";
import { Alert, Spinner, Table } from "react-bootstrap";
import { checkImage } from "../../service/base/utils";

const HistoryOrder = ({ id }) => {
  const [history, setHistory] = useState([]);
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const decodedToken = await decodeToken();
        const userId = decodedToken.id;
        const data = await OrderService.getHistoryOrder(userId);
        const orders = data;
        setHistory(orders);
      } catch (error) {
        console.error(error);
      } finally {
        setIsLoading(false); // Đặt isLoading thành false trong cả trường hợp thành công và thất bại
      }
    };
    fetchData();
  }, [id]);

  const fetchImageUrls = async () => {
    const urls = {};
    for (const order of history) {
      for (const item of order.items) {
        const checkedUrl = await checkImage(item.product.image);
        urls[item.product.id] = checkedUrl;
      }
    }
    return urls;
  };

  const [imageUrl, setImageUrl] = useState({});
  let index = 1;

  useEffect(() => {
    const fetchImages = async () => {
      const urls = await fetchImageUrls();
      setImageUrl(urls);
    };
    fetchImages();
  }, [history]);

  const sortedHistory = [...history].sort((a, b) => b.id - a.id);
  return (
    <div>
      <h1>Order History</h1>
      {isLoading ? (
        <Spinner animation="border" role="status">
          <span className="visually-hidden">Loading...</span>
        </Spinner>
      ) : (
        <>
          {sortedHistory.length === 0 ? (
            <Alert variant="info">
              Vui lòng đặt order để hiển thị được lịch sử!
            </Alert>
          ) : (
            <Table striped bordered>
              <thead>
                <tr>
                  <th>STT</th>
                  <th>Status</th>
                  <th>Product Name</th>
                  <th>Product Quantity</th>
                  <th>Product Price</th>
                  <th>Product Description</th>
                  <th>Product Image</th>
                  <th>Total Sold</th>
                </tr>
              </thead>
              <tbody>
                {sortedHistory.map((order) => (
                  <tr key={order.id}>
                    <td>{index++}</td>
                    <td>{order.status}</td>
                    {order.items.map((item) => (
                      <React.Fragment key={item.id}>
                        <td>{item.product.name}</td>
                        <td>{item.quantity}</td>
                        <td>{item.price}</td>
                        <td>{item.product.description}</td>
                        <td>
                          <img
                            src={
                              imageUrl[item.product.id]
                            }
                            alt={item.product.name}
                            style={{ maxWidth: "100px" }}
                          />
                        </td>

                      </React.Fragment>
                    ))
                    }
                    <td>{order.total}</td>
                  </tr>
                ))}
              </tbody>
            </Table>
          )}
        </>
      )}
    </div>
  );
};
export default HistoryOrder;
