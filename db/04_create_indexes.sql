-- AUDITORÍA
CREATE INDEX idx_admin_actions_actor ON admin_actions_log(actor_id);
CREATE INDEX idx_admin_actions_entity ON admin_actions_log(target_entity);
CREATE INDEX idx_admin_actions_date ON admin_actions_log(created_at);

-- ÍNDICES ADICIONALES
CREATE INDEX idx_reservations_user ON reservations(user_id);
CREATE INDEX idx_reservations_status ON reservations(status_id);
CREATE INDEX idx_payments_reservation ON payments(reservation_id);
CREATE INDEX idx_room_prices_room_type ON room_prices(room_type_id);
CREATE INDEX idx_users_email ON users(email);
CREATE INDEX idx_users_username ON users(username);
