import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { Container } from 'react-bootstrap';
import Header from './components/Header';
import Footer from './components/Footer';
import HomePage from './pages/HomePage';
import AuthPage from './pages/AuthPage';
import FlightSearchPage from './pages/FlightSearchPage';
import FlightDetailsPage from './pages/FlightDetailsPage';
import BookingPage from './pages/BookingPage';
import PaymentPage from './pages/PaymentPage';
import CheckInPage from './pages/CheckInPage.jsx';
import UserDashboardPage from './pages/UserDashboardPage';
import './App.css';

function App() {
  return (
    <Router>
      <Header />
      {/* The main content area with dynamic padding */}
      <main>
        <Routes>
          {/* Route for HomePage which should be full-width */}
          <Route path="/" element={<HomePage />} />

          {/* Routes for all other pages which should be contained */}
          <Route
            path="/*"
            element={
              <Container className="my-4">
                <Routes>
                  <Route path="/login" element={<AuthPage />} />
                  <Route path="/search" element={<FlightSearchPage />} />
                  <Route path="/flight/:flightId" element={<FlightDetailsPage />} />
                  <Route path="/book/:flightId" element={<BookingPage />} />
                  <Route path="/payment" element={<PaymentPage />} />
                  <Route path="/check-in" element={<CheckInPage />} />
                  <Route path="/dashboard" element={<UserDashboardPage />} />
                </Routes>
              </Container>
            }
          />
        </Routes>
      </main>
      <Footer />
    </Router>
  );
}

export default App;