import React from 'react';
import { Container, Form, Button, Card, Row, Col, Alert } from 'react-bootstrap';
import { FaCcVisa, FaCcMastercard, FaCcAmex } from 'react-icons/fa';
import { useNavigate } from 'react-router-dom';

const PaymentPage = () => {
    const navigate = useNavigate();
    const [paymentSuccess, setPaymentSuccess] = React.useState(false);

    const handlePayment = (e) => {
        e.preventDefault();
        setPaymentSuccess(true);
        setTimeout(() => {
            navigate('/dashboard'); 
        }, 2000);
    }
  return (
    <Container>
      <h2 className="my-4">Payment</h2>
      {paymentSuccess && <Alert variant="success">Payment Successful! Redirecting to your dashboard...</Alert>}
      <Card className="p-4">
        <div className="mb-3 text-center">
            <FaCcVisa size={40} className="me-2" />
            <FaCcMastercard size={40} className="me-2" />
            <FaCcAmex size={40} />
        </div>
        <Form onSubmit={handlePayment} hidden={paymentSuccess}>
          <Form.Group className="mb-3" controlId="cardName">
            <Form.Label>Name on Card</Form.Label>
            <Form.Control type="text" placeholder="Full name as displayed on card" required />
          </Form.Group>
          <Form.Group className="mb-3" controlId="cardNumber">
            <Form.Label>Card Number</Form.Label>
            <Form.Control type="text" placeholder="Enter card number" required />
          </Form.Group>
          <Row>
            <Col md={6}>
              <Form.Group className="mb-3" controlId="expiryDate">
                <Form.Label>Expiry Date</Form.Label>
                <Form.Control type="text" placeholder="MM/YY" required />
              </Form.Group>
            </Col>
            <Col md={6}>
              <Form.Group className="mb-3" controlId="cvv">
                <Form.Label>CVV</Form.Label>
                <Form.Control type="text" placeholder="CVV" required />
              </Form.Group>
            </Col>
          </Row>
          <div className="d-grid">
            <Button variant="success" type="submit" size="lg">Pay Now ($350.00)</Button>
          </div>
        </Form>
      </Card>
    </Container>
  );
};

export default PaymentPage;