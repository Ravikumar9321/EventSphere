import React from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Home from './Management/Home';
import AttendeeManage from './Management/AttendeeManage';
import ARegistration from './RegistrationForms/AttendeeRegistrationForm';
import RegisterDetails from './Management/RegistrationDetails';     
import RegisterManage from './Management/RegisterManage';     
import EventManage from './Management/EventManage';
import EventReg from './RegistrationForms/EventRegistrationForm';
import VenueManage from './Management/VenueManage';
import VenueForm from './RegistrationForms/VenueForm';
import Organizermanage from './Management/OrganizerManage';
import OrganizeForm from './RegistrationForms/OrganizeForm';
import AuthPage from './api/Login';
import ProtectedLayout from './api/ProtectedLayout';

function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<AuthPage />} />

                {/* Protected routes */}
                <Route element={<ProtectedLayout />}>
                    <Route path="/home" element={<Home />} />
                    <Route path="/attendee" element={<AttendeeManage />} />
                    <Route path="/register" element={<ARegistration />} />      
                    <Route path="/registers" element={<RegisterDetails />} />      
                    <Route path="/register/:id" element={<RegisterManage />} />   
                    <Route path="/eventManage" element={<EventManage />} />
                    <Route path="/eventReg/:id" element={<EventReg />} />
                    <Route path="/venueManage" element={<VenueManage />} />
                    <Route path="/vRegister" element={<VenueForm />} />
                    <Route path="/orgManage" element={<Organizermanage />} />
                    <Route path="/orgRegister" element={<OrganizeForm />} />
                </Route>
            </Routes>
        </BrowserRouter>
    );
}

export default App;
