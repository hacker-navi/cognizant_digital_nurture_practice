/* Community Event Portal JavaScript */
(function () {
  'use strict';

  const state = {
    events: [
      { id: 1, name: 'Community Fair', date: '2026-06-10', seats: 12, category: 'festival', location: 'Downtown Plaza' },
      { id: 2, name: 'Park Cleanup', date: '2026-06-12', seats: 8, category: 'volunteer', location: 'Riverside Park' },
      { id: 3, name: 'Music in the Square', date: '2026-06-18', seats: 0, category: 'music', location: 'Market Square' },
      { id: 4, name: 'Baking Workshop', date: '2026-06-22', seats: 20, category: 'workshop', location: 'Community Center' }
    ],
    selectedCategory: 'all',
    searchTerm: ''
  };

  class Event {
    constructor({ id, name, date, seats, category, location }) {
      this.id = id;
      this.name = name;
      this.date = date;
      this.seats = seats;
      this.category = category;
      this.location = location;
    }
  }

  Event.prototype.checkAvailability = function () {
    return this.seats > 0;
  };

  const categoryRegistrationCount = createCategoryCounter();

  function createCategoryCounter() {
    const totals = {};
    return function (category) {
      totals[category] = (totals[category] || 0) + 1;
      return totals[category];
    };
  }

  function log(message) {
    console.log(message);
  }

  function $(selector) {
    return document.querySelector(selector);
  }

  function $all(selector) {
    return Array.from(document.querySelectorAll(selector));
  }

  function parseDate(value) {
    return new Date(`${value}T00:00:00`);
  }

  function isUpcoming(event) {
    return parseDate(event.date) >= new Date(new Date().toDateString());
  }

  function addEvent(event) {
    state.events.push(new Event(event));
    renderEvents();
  }

  function registerUser(eventId, button) {
    try {
      const event = state.events.find((item) => item.id === eventId);
      if (!event) {
        throw new Error('Event not found');
      }

      if (!isUpcoming(event) || event.seats <= 0) {
        throw new Error('This event is unavailable');
      }

      event.seats -= 1;
      const totalForCategory = categoryRegistrationCount(event.category);
      updateEventStatus(`Registered for ${event.name}. Seats left: ${event.seats}. Category registrations: ${totalForCategory}`);
      renderEvents();

      if (button) {
        button.disabled = true;
        button.textContent = 'Registered';
      }
    } catch (error) {
      updateEventStatus(error.message, true);
      console.error(error);
    }
  }

  function filterEventsByCategory(category, callback) {
    const clone = [...state.events];
    const filtered = clone.filter((event) => {
      const matchesCategory = category === 'all' || event.category === category;
      const matchesSearch = event.name.toLowerCase().includes(state.searchTerm.toLowerCase());
      return matchesCategory && matchesSearch && isUpcoming(event) && event.seats > 0;
    });

    if (typeof callback === 'function') {
      callback(filtered);
    }

    return filtered;
  }

  function updateEventStatus(message, isError = false) {
    const status = $('#eventStatus');
    if (!status) {
      return;
    }

    status.textContent = message;
    status.className = isError ? 'status error' : 'status success';
  }

  function validatePhone() {
    const phone = document.querySelector('#phone');
    if (!phone) {
      return;
    }

    const isValid = /^\d{3}-\d{3}-\d{4}$/.test(phone.value.trim());
    if (phone.value && !isValid) {
      updateEventStatus('Please enter a phone number like 123-456-7890', true);
    }
  }

  function updateFee() {
    const eventType = document.querySelector('#eventType');
    const feeValue = document.querySelector('#feeValue');
    if (!eventType || !feeValue) {
      return;
    }

    const feeMap = {
      free: 'Free',
      low: '$10',
      high: '$25'
    };

    feeValue.textContent = feeMap[eventType.value] || 'Free';
    localStorage.setItem('preferredEventType', eventType.value);
  }

  function countChars() {
    const textarea = document.querySelector('#message');
    const count = document.querySelector('#charCount');
    if (textarea && count) {
      count.textContent = textarea.value.length.toString();
    }
  }

  function videoReady() {
    const status = document.querySelector('#videoStatus');
    if (status) {
      status.textContent = 'Video ready to play';
    }
  }

  function toggleEnlarge(image) {
    image.classList.toggle('enlarged');
  }

  function clearPreferences() {
    localStorage.clear();
    sessionStorage.clear();
    updateEventStatus('Preferences cleared.');
  }

  function findNearbyEvents() {
    const output = document.querySelector('#geoResult');
    if (!navigator.geolocation || !output) {
      return;
    }

    output.textContent = 'Finding nearby events...';
    navigator.geolocation.getCurrentPosition(
      (position) => {
        output.textContent = `Latitude: ${position.coords.latitude.toFixed(4)}, Longitude: ${position.coords.longitude.toFixed(4)}`;
      },
      (error) => {
        if (error.code === 1) {
          output.textContent = 'Permission denied.';
        } else if (error.code === 3) {
          output.textContent = 'Location request timed out.';
        } else {
          output.textContent = 'Unable to get location.';
        }
      },
      { enableHighAccuracy: true, timeout: 10000, maximumAge: 0 }
    );
  }

  function submitForm() {
    const form = document.querySelector('#registrationForm');
    if (!form) {
      return;
    }

    const formData = new FormData(form);
    const payload = Object.fromEntries(formData.entries());
    console.log('Registration payload:', payload);
    updateEventStatus(`Registration captured for ${payload.name || 'guest'}`);
    sessionStorage.setItem('registrationStarted', 'true');
  }

  function renderEvents() {
    const container = $('#eventCards');
    if (!container) {
      return;
    }

    container.innerHTML = '';
    const filtered = filterEventsByCategory(state.selectedCategory, () => {});

    filtered.forEach((event) => {
      const card = document.createElement('article');
      card.className = 'eventCard';

      const displayName = `Workshop on ${event.name}`;
      const availability = event.checkAvailability ? event.checkAvailability() : event.seats > 0;

      card.innerHTML = `
        <h3>${displayName}</h3>
        <p><strong>Date:</strong> ${event.date}</p>
        <p><strong>Location:</strong> ${event.location}</p>
        <p><strong>Category:</strong> ${event.category}</p>
        <p><strong>Seats:</strong> ${event.seats}</p>
        <p><strong>Status:</strong> ${availability ? 'Available' : 'Full'}</p>
        <button type="button" class="registerBtn" data-id="${event.id}">Register</button>
      `;

      container.appendChild(card);
    });

    bindRegisterButtons();
  }

  function bindRegisterButtons() {
    $all('.registerBtn').forEach((button) => {
      button.onclick = () => registerUser(Number(button.dataset.id), button);
    });
  }

  function ensureControls() {
    const host = $('#jsControls');
    if (!host) {
      return;
    }

    host.innerHTML = `
      <label for="categoryFilter">Filter by category</label>
      <select id="categoryFilter">
        <option value="all">All</option>
        <option value="festival">Festival</option>
        <option value="volunteer">Volunteer</option>
        <option value="music">Music</option>
        <option value="workshop">Workshop</option>
      </select>
      <label for="quickSearch">Search events</label>
      <input id="quickSearch" type="search" placeholder="Type a name">
      <button type="button" id="addDemoEvent">Add Demo Event</button>
      <div id="eventStatus" class="status"></div>
      <div id="spinner" class="spinner" hidden>Loading events...</div>
    `;

    $('#categoryFilter').onchange = (event) => {
      state.selectedCategory = event.target.value;
      renderEvents();
    };

    $('#quickSearch').onkeydown = (event) => {
      state.searchTerm = event.target.value;
      if (event.key === 'Enter') {
        renderEvents();
      }
    };

    $('#addDemoEvent').onclick = () => {
      addEvent({
        id: Date.now(),
        name: 'Neighborhood Meetup',
        date: '2026-07-01',
        seats: 15,
        category: 'festival',
        location: 'Town Hall'
      });
      log('Demo event added');
    };
  }

  function initForm() {
    const form = $('#registrationForm');
    if (!form) {
      return;
    }

    form.addEventListener('submit', async (event) => {
      event.preventDefault();
      const formData = new FormData(form);
      const payload = Object.fromEntries(formData.entries());

      if (!payload.name || !payload.email || !payload.eventType) {
        updateEventStatus('Please fill in all required fields.', true);
        return;
      }

      updateEventStatus('Submitting registration...');
      console.log('Form submission step 1:', payload);
      sessionStorage.setItem('registrationStarted', 'true');

      try {
        const response = await submitRegistration(payload);
        updateEventStatus(response.message);
      } catch (error) {
        console.error('Registration failed:', error);
        updateEventStatus('Registration failed. Please try again.', true);
      }
    });
  }

  function submitRegistration(payload) {
    return new Promise((resolve, reject) => {
      setTimeout(() => {
        fetch('https://jsonplaceholder.typicode.com/posts', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(payload)
        })
          .then((response) => {
            if (!response.ok) {
              throw new Error('Network response was not ok');
            }
            return response.json();
          })
          .then((data) => resolve({ message: `Registration saved for ${payload.name}`, data }))
          .catch(reject);
      }, 800);
    });
  }

  async function loadEvents() {
    const spinner = $('#spinner');
    if (spinner) {
      spinner.hidden = false;
    }

    try {
      const response = await fetch('https://jsonplaceholder.typicode.com/todos/1');
      const data = await response.json();
      console.log('Async/Await mock fetch result:', data);
      renderEvents();
    } catch (error) {
      console.error('Failed to load mock events:', error);
    } finally {
      if (spinner) {
        spinner.hidden = true;
      }
    }
  }

  function bindJQueryDemo() {
    if (window.jQuery) {
      window.jQuery('#registerBtn').click(function () {
        window.jQuery('.eventCard').fadeIn().fadeOut(120).fadeIn(120);
      });
    }
  }

  function init() {
    log('Welcome to the Community Portal');
    window.alert('Page fully loaded');

    if ($('#portalIntro')) {
      $('#portalIntro').textContent = 'Upcoming and available events are loaded dynamically below.';
    }

    ensureControls();
    initForm();
    renderEvents();
    loadEvents();
    bindJQueryDemo();

    validatePhone();
    updateFee();
    countChars();

    const registerButton = $('#registerBtn');
    if (registerButton) {
      registerButton.onclick = submitForm;
    }

    const clearButton = $('#clearPrefs');
    if (clearButton) {
      clearButton.onclick = clearPreferences;
    }

    const geoButton = $('#findNearby');
    if (geoButton) {
      geoButton.onclick = findNearbyEvents;
    }

    const video = $('#promoVideo');
    if (video) {
      video.oncanplay = videoReady;
    }

    const phone = $('#phone');
    if (phone) {
      phone.onblur = validatePhone;
    }

    const message = $('#message');
    if (message) {
      message.onkeydown = countChars;
    }

    const eventType = $('#eventType');
    if (eventType) {
      eventType.onchange = updateFee;
      const preferred = localStorage.getItem('preferredEventType');
      if (preferred) {
        eventType.value = preferred;
        updateFee();
      }
    }

    if (window.jQuery) {
      window.jQuery('#registerBtn').click(() => {
        window.jQuery('.eventCard').fadeIn().fadeOut(120).fadeIn(120);
      });
    }

    document.addEventListener('keydown', (event) => {
      if (event.key === 'Escape') {
        updateEventStatus('Quick search cleared');
        state.searchTerm = '';
        const search = $('#quickSearch');
        if (search) {
          search.value = '';
        }
        renderEvents();
      }
    });
  }

  document.addEventListener('DOMContentLoaded', init);

  window.addEvent = addEvent;
  window.registerUser = registerUser;
  window.filterEventsByCategory = filterEventsByCategory;
  window.toggleVisibilityDemo = function (selector) {
    const element = $(selector);
    if (!element) {
      return;
    }
    element.style.display = element.style.display === 'none' ? '' : 'none';
  };
  window.validatePhone = validatePhone;
  window.updateFee = updateFee;
  window.countChars = countChars;
  window.videoReady = videoReady;
  window.toggleEnlarge = toggleEnlarge;
  window.clearPreferences = clearPreferences;
  window.findNearbyEvents = findNearbyEvents;
  window.submitForm = submitForm;
})();
