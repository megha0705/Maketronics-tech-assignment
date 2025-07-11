

let allBooks = [];

fetch('https://topbooklist-2.onrender.com/api/getAllBook')
  .then(response => response.json())
  .then(books => {
    allBooks = books;
    renderBooks(allBooks);
  })
  .catch(error => {
    console.error('Error fetching books:', error);
    const grid = document.getElementById('productGrid');
    grid.innerHTML = '<p class="text-danger">Failed to load books. Please try again later.</p>';
  });


function renderBooks(books) {
  const grid = document.getElementById('productGrid');
  grid.innerHTML = '';

  if (books.length === 0) {
    grid.innerHTML = '<p class="text-light">No books found matching your search.</p>';
    return;
  }

  books.forEach(book => {
    const cardHTML = `
      <div class="col-12 col-md-6 col-lg-3 mb-4">
        <div class="card h-100">
          <img src="${book.imgUrl}" class="card-img-top" alt="Book Cover">
          <div class="card-body d-flex flex-column">
            <h5 class="card-title">${book.title}</h5>
            <p class="card-text">${book.description}</p>
            <a href="${book.link}" target="_blank" class="btn btn-primary mt-auto">View Source</a>
          </div>
        </div>
      </div>
    `;
    grid.insertAdjacentHTML('beforeend', cardHTML);
  });
}


document.getElementById('searchButton').addEventListener('click', () => {
  performSearch();
});


document.getElementById('searchInput').addEventListener('keyup', (e) => {
  if (e.key === 'Enter') {
    performSearch();
  }
});


function performSearch() {
  const query = document.getElementById('searchInput').value.trim().toLowerCase();

  if (!query) {

    renderBooks(allBooks);
    return;
  }

  const filtered = allBooks.filter(book =>
    book.title.toLowerCase().includes(query) ||
    book.description.toLowerCase().includes(query)
  );

  renderBooks(filtered);
}

document.addEventListener("DOMContentLoaded", function () {
    const refreshBtn = document.getElementById("refreshIcon");

    refreshBtn.addEventListener("click", () => {
        fetch("https://topbooklist-2.onrender.com/api/refresh", {
            method: "GET"
        })
        .then(response => {
            if (!response.ok) {
                throw new Error("Failed to refresh data");
            }
            return response.text();
        })
        .then(data => {
            console.log("Data refreshed:", data);

            location.reload();
        })
        .catch(error => {
            console.error("Error refreshing data:", error);
            alert("Refresh failed. Check console for details.");
        });
    });
});
