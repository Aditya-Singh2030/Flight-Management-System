import React from 'react';
import { useParams, Link } from 'react-router-dom';
import { Container, Card, Button, Row, Col } from 'react-bootstrap';

const FlightDetailsPage = () => {
  const { flightId } = useParams();
  const flight = {
    id: flightId,
    from: 'New York (JFK)',
    to: 'Los Angeles (LAX)',
    departure: '08:00 AM',
    arrival: '11:30 AM',
    duration: '5h 30m',
    airline: 'United Airlines',
    price: 350,
    aircraft: 'Boeing 737',
    fareClass: 'Economy'
  };

  return (
    <Container>
      <h2 className="my-4">Flight Details</h2>
      <Card>
        <Card.Header as="h5">Flight {flight.id}</Card.Header>
        <Card.Body>
          <Row>
            <Col md={8}>
              <Card.Title className="display-6">{flight.from} → {flight.to}</Card.Title>
              <Card.Text><strong>Airline:</strong> {flight.airline}</Card.Text>
              <Card.Text><strong>Aircraft:</strong> {flight.aircraft}</Card.Text>
              <Card.Text><strong>Duration:</strong> {flight.duration}</Card.Text>
              <Card.Text><strong>Fare Class:</strong> {flight.fareClass}</Card.Text>
            </Col>
            <Col md={4} className="text-md-end text-center mt-3 mt-md-0">
                <h3>${flight.price}</h3>
                <p>per passenger</p>
                <Link to={`/book/${flight.id}`}>
                    <Button variant="success" size="lg">Book Now</Button>
                </Link>
            </Col>
          </Row>
        </Card.Body>
      </Card>
    </Container>
  );
};

export default FlightDetailsPage;