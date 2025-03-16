import requests
import sqlite3
from bs4 import BeautifulSoup
def scrape_and_store(url):
    """Scrapes all paragraphs from a Wikipedia page, previews them, and stores them in an SQLite database."""

    # Set user-agent to prevent request blocking
    headers = {"User-Agent": "Mozilla/5.0"}
    response = requests.get(url, headers=headers)

    if response.status_code != 200:
        print(f"Failed to fetch page: {response.status_code}")
        return
    soup = BeautifulSoup(response.text, "html.parser")
    paragraphs = [
        para.get_text(strip=True)
        for para in soup.find_all("p")
        if para.get_text(strip=True)
    ]
    if not paragraphs:
        print("No paragraph data found!")
        return

    print("\n--- Preview of Scraped Paragraphs ---\n")
    for i, para in enumerate(paragraphs[:5], start=1):  
        print(f"{i}. {para[:300]}...\n") 

    confirm = input("Do you want to save the data? (yes/no): ").strip().lower()
    if confirm != "yes":
        print("Data not saved.")
        return

    # Connecting to SQLite database
    conn = sqlite3.connect("wikipedia_data.db")
    cursor = conn.cursor()

    # Create table if not exists
    cursor.execute(
        """
        CREATE TABLE IF NOT EXISTS wiki_paragraphs (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            url TEXT,
            paragraph TEXT
        )
        """
    )
    # Inserting data
    cursor.executemany(
        "INSERT INTO wiki_paragraphs (url, paragraph) VALUES (?, ?)",
        [(url, para) for para in paragraphs],
    )
    conn.commit()
    conn.close()
    print("Data successfully stored in wikipedia_data.db")

if __name__ == "__main__":
    wiki_url = input("Enter URL To Scrape: ").strip()
    scrape_and_store(wiki_url)