class Motor:
    def __init__(self, pin_a, pin_b, pin_pwm):
        self.pin_a = pin_a
        self.pin_b = pin_b
        self.pin_pwm = pin_pwm
        self.pwm = PWM(Pin(self.pin_pwm), freq=1000)
        self.direction = 1

    def forward(self, speed=0.5):
        self.pwm.duty(int(speed * 1023))
        self.direction = 1
        digitalWrite(self.pin_a, 1)
        digitalWrite(self.pin_b, 0)

    def reverse(self, speed=0.5):
        self.pwm.duty(int(speed * 1023))
        self.direction = -1
        digitalWrite(self.pin_a, 0)
        digitalWrite(self.pin_b, 1)

    def stop(self):
        self.pwm.duty(0)
        digitalWrite(self.pin_a, 0)
        digitalWrite(self.pin_b, 0)
