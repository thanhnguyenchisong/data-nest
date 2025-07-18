const puppeteer = require('puppeteer-extra');
const StealthPlugin = require('puppeteer-extra-plugin-stealth');
const ProxyChain = require('proxy-chain');
const cheerio = require('cheerio');
const axios = require('axios');

puppeteer.use(StealthPlugin());

const PROXY_LIST = [
    'http://user:pass@proxy1:port',
    'http://user:pass@proxy2:port',
    'http://user:pass@proxy3:port'
];

async function crawlUrl(targetUrl) {
    const oldProxyUrl = PROXY_LIST[Math.floor(Math.random() * PROXY_LIST.length)];
    const proxyUrl = await ProxyChain.anonymizeProxy(oldProxyUrl);

    const browser = await puppeteer.launch({
        headless: false,
        args: [
            `--proxy-server=${proxyUrl}`,
            '--no-sandbox',
            '--disable-setuid-sandbox',
            '--disable-blink-features=AutomationControlled',
        ],
    });

    const page = await browser.newPage();
    await page.setUserAgent('Mozilla/5.0 (Windows NT 10.0; Win64; x64)');
    await page.goto(targetUrl, { waitUntil: 'networkidle2', timeout: 60000 });
    await page.waitForSelector('body');

    const html = await page.content();
    await browser.close();

    return html;
}

async function parseAndPush(html) {
    const $ = cheerio.load(html);
    const data = [];

    $('a.product-link').each((_, el) => {
        data.push({
            title: $(el).find('.product-title').text().trim(),
            url: $(el).attr('href'),
            price: $(el).find('.product-price').text().trim(),
        });
    });

    if (data.length > 0) {
        await axios.post('http://localhost:8080/api/realestate/crawl', data);
    }
}

(async () => {
    try {
        const html = await crawlUrl('https://batdongsan.com.vn/nha-dat-ban-hiep-hoa-bg');
        await parseAndPush(html);
        console.log('Done one batch.');
    } catch (err) {
        console.error(err);
    }
})();
