import React from 'react';
import { Container, Form, Button, Card, Row, Col } from 'react-bootstrap';
import { useNavigate, useParams } from 'react-router-dom';

const BookingPage = () => {
    const navigate = useNavigate();
    const { flightId } = useParams();

    const handleBookingSubmit = (e) => {
        e.preventDefault();
        navigate('/payment');
    }

  return (
    <Container>
      <h2 className="my-4">Passenger Information for Flight {flightId}</h2>
      <Card className="p-4">
        <Form onSubmit={handleBookingSubmit}>
          <h4>Passenger 1 (Primary Contact)</h4>
          <Row className="mb-3">
            <Col md={6}>
              <Form.Group controlId="firstName">
                <Form.Label>First Name</Form.Label>
                <Form.Control type="text" placeholder="Enter first name" required />
              </Form.Group>
            </Col>
            <Col md={6}>
              <Form.Group controlId="lastName">
                <Form.Label>Last Name</Form.Label>
                <Form.Control type="text" placeholder="Enter last name" required />
              </Form.Group>
            </Col>
          </Row>
          <Row className="mb-3">
            <Col md={6}>
              <Form.Group controlId="dob">
                <Form.Label>Date of Birth</Form.Label>
                <Form.Control type="date" required />
              </Form.Group>
            </Col>
             <Col md={6}>
              <Form.Group controlId="email">
                <Form.Label>Contact Email</Form.Label>
                <Form.Control type="email" placeholder="Enter email" required />
              </Form.Group>
            </Col>
          </Row>
          
          <div className="d-grid gap-2 mt-4">
            <Button variant="primary" type="submit" size="lg">
              Proceed to Payment
            </Button>
          </div>
        </Form>
      </Card>
    </Container>
  );
};

export default BookingPage;