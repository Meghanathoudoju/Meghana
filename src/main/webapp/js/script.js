// script.js - General Dashboard and Form Interactions

document.addEventListener('DOMContentLoaded', () => {
    // Basic Form Validation for Auth Forms
    const authForms = document.querySelectorAll('form[action^="auth"]');
    
    authForms.forEach(form => {
        form.addEventListener('submit', (e) => {
            let valid = true;
            const inputs = form.querySelectorAll('input[required]');
            
            inputs.forEach(input => {
                if (!input.value.trim()) {
                    valid = false;
                    input.style.borderColor = 'var(--danger-color)';
                } else {
                    input.style.borderColor = 'var(--border-color)';
                }
            });

            // Password confirmation matching if it exists
            const pwd = form.querySelector('input[name="password"]');
            const confirmPwd = form.querySelector('input[name="confirmPassword"]');
            
            if (pwd && confirmPwd) {
                if (pwd.value !== confirmPwd.value) {
                    valid = false;
                    confirmPwd.style.borderColor = 'var(--danger-color)';
                    alert('Passwords do not match!');
                }
            }

            if (!valid) {
                e.preventDefault();
            }
        });
    });

    // Dashboard Sidebar Active State Management
    // Extracted dashboard sections script logic
    const navLinks = document.querySelectorAll('.sidebar a[id^="nav-"]');
    const sections = document.querySelectorAll('section.content-section');

    if (navLinks.length > 0 && sections.length > 0) {
        window.showSection = function(id) {
            sections.forEach(s => {
                s.style.display = 'none';
                s.style.opacity = '0';
            });
            
            navLinks.forEach(a => a.classList.remove('active'));
            
            const targetSection = document.getElementById(id);
            if(targetSection) {
                targetSection.style.display = 'block';
                // Trigger reflow
                void targetSection.offsetWidth;
                targetSection.style.opacity = '1';
                targetSection.style.transition = 'opacity 0.4s ease-in-out';
            }
            
            const targetNav = document.getElementById('nav-' + id);
            if(targetNav) {
                targetNav.classList.add('active');
            }
        }
    }

    // Auto dismiss alerts after 5 seconds
    const alerts = document.querySelectorAll('.alert');
    alerts.forEach(alert => {
        setTimeout(() => {
            alert.style.transition = 'opacity 0.5s ease';
            alert.style.opacity = '0';
            setTimeout(() => alert.remove(), 500);
        }, 5000);
    });
});
